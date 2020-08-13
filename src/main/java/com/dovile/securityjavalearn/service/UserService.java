package com.dovile.securityjavalearn.service;

import com.dovile.securityjavalearn.domain.UserRequest;
import com.dovile.securityjavalearn.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author barkauskaite.dovile@gmail.com
 */
public interface UserService extends UserDetailsService {
    /**
     *
     * @param userRequest
     * @return new User
     */
    User save(UserRequest userRequest );
    /**
     *
     * @param s
     * @return user by email
     * @throws UsernameNotFoundException
     */
    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
    /**
     *
     * @param email
     * @return check if email exist
     */
    Boolean existByEmail(String email);
}
