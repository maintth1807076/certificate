package com.heleyquin.certificate.service;

import javax.swing.text.html.Option;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.heleyquin.certificate.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<com.heleyquin.certificate.entity.User> user = userRepository.findById(s);
        if(!user.isPresent()) {
            throw new UsernameNotFoundException(s);
        }
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        return new User(user.get().getUsername(), user.get().getPassword(), grantedAuthoritySet);
    }

    public com.heleyquin.certificate.entity.User createUser(com.heleyquin.certificate.entity.User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
