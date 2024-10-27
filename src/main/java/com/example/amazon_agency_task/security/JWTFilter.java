package com.example.amazon_agency_task.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.amazon_agency_task.constants.StringConstants.AUTHORIZATION_HEADER;
import static com.example.amazon_agency_task.constants.StringConstants.BEARER_AUTHORIZATION_HEADER;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final var authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_AUTHORIZATION_HEADER)) {
            jwt = authorizationHeader.substring(7);
            username = this.jwtUtil.extractUsername(jwt);
        }

        if (nonNull(username) && isNull(SecurityContextHolder.getContext().getAuthentication())) {
            var commaSeparatedListOfAuthorities = this.jwtUtil.extractAuthorities(jwt);
            var authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities);
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities
            );

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        chain.doFilter(request, response);
    }
}
