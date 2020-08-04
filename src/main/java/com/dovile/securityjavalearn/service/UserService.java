package com.dovile.securityjavalearn.service;

import com.dovile.securityjavalearn.domain.UserRequest;
import com.dovile.securityjavalearn.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService extends UserDetailsService {
    User save(UserRequest userRequest);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
