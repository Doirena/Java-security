package com.dovile.securityjavalearn.service;


import com.dovile.securityjavalearn.domain.UserRequest;
import com.dovile.securityjavalearn.entities.Role;
import com.dovile.securityjavalearn.entities.User;
import com.dovile.securityjavalearn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRequest userRequest) {
        User user = new User(userRequest.getFirstName(),
                userRequest.getLastName(), userRequest.getEmail(),
                passwordEncoder.encode(userRequest.getPassword()), Arrays.asList(new Role("ROLE_USER")));

        for (User u : userRepository.findAll()) {
            if (userRequest.getEmail().equals(u.getEmail())) {
                //throw new Exception("Someone already has that username.");
                // throw new DuplicateKeyException("Someone already has that username.");
                throw new IllegalArgumentException("Someone already has that username.");
            }
        }

        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
