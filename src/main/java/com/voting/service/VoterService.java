package com.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.voting.entity.Voter;
import com.voting.repository.VoterRepository;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Voter registerVoter(Voter voter) {

        voter.setPassword(passwordEncoder.encode(voter.getPassword()));

        if (voterRepository.existsByEmail(voter.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (voterRepository.existsByVoterId(voter.getVoterId())) {
            throw new RuntimeException("Voter ID already exists");
        }

        return voterRepository.save(voter);
     
    }
}