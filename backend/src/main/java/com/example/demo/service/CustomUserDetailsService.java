package com.example.demo.service;

import com.example.demo.entity.Lessor;
import com.example.demo.repository.LessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final LessorRepository lessorRepository;

    @Override
    public UserDetails loadUserByUsername(String lessorName) throws UsernameNotFoundException {
        Lessor lessor = lessorRepository.findByLessorName(lessorName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + lessorName));

        return org.springframework.security.core.userdetails.User.builder()
                .username(lessor.getLessorName())
                .password(lessor.getLessorPassword())
                .build();
    }
}