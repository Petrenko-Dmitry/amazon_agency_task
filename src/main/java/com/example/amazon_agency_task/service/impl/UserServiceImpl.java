package com.example.amazon_agency_task.service.impl;

import com.example.amazon_agency_task.entity.User;
import com.example.amazon_agency_task.repository.UserRepository;
import com.example.amazon_agency_task.security.AuthRequest;
import com.example.amazon_agency_task.security.AuthResponse;
import com.example.amazon_agency_task.security.JWTUtil;
import com.example.amazon_agency_task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.example.amazon_agency_task.constants.StringConstants.INCORRECT_NAME_OR_PASSWORD_MESSAGE;
import static java.util.Objects.isNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtil jwtTokenUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void register(AuthRequest user) {
        var existUser = this.userRepository.findByUsername(user.name());
        if (isNull(existUser)) {
            this.userRepository.save(
                    new User(
                            user.name(),
                            this.passwordEncoder.encode(user.password())
                    )
            );
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

    }

    @Override
    public AuthResponse login(AuthRequest user) {
        Authentication authentication;
        try {
            authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.name(), user.password()));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, INCORRECT_NAME_OR_PASSWORD_MESSAGE, e);
        }
        var jwt = this.jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        return new AuthResponse(jwt);
    }
}
