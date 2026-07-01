package com.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.voting.entity.Voter;
import com.voting.repository.VoterRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private VoterRepository voterRepository;

    @Override
    public UserDetails loadUserByUsername(String voterId)
            throws UsernameNotFoundException {

        Voter voter = voterRepository.findByVoterId(voterId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Voter not found"));

        return User.builder()
                .username(voter.getVoterId())
                .password(voter.getPassword())
                .roles("USER")
                .build();
    }
}