package com.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.voting.entity.Voter;
import com.voting.repository.VoterRepository;

@RestController
@RequestMapping("/voter")
public class VoterController {

    @Autowired
    private VoterRepository voterRepository;

    @PostMapping("/register")
    public Voter registerVoter(@RequestBody Voter voter) {
        return voterRepository.save(voter);
    }

    @GetMapping
    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }
    @GetMapping("/{voterId}")
    public Voter getVoterByVoterId(@PathVariable String voterId) {

        return voterRepository.findByVoterId(voterId)
                .orElse(null);
    }
}