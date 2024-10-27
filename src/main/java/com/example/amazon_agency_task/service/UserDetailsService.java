package com.example.amazon_agency_task.service;

import com.example.amazon_agency_task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.amazon_agency_task.constants.StringConstants.UNKNOWN_USER_FORMAT;
import static java.util.Objects.isNull;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var myUser = userRepository.findByUsername(username);
        if (isNull(myUser)) {
            throw new UsernameNotFoundException(String.format(UNKNOWN_USER_FORMAT, username));
        }
        return User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(myUser.getRole().toString())
                .build();
    }
}
