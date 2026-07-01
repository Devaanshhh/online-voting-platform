package com.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.voting.service.VoterService;
import com.voting.entity.Voter;
import com.voting.repository.VoterRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/voter")
public class VoterController {
    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private VoterService voterService;
    @PostMapping("/register")
    public ResponseEntity<Voter> registerVoter(@Valid @RequestBody Voter voter) {

        Voter savedVoter = voterService.registerVoter(voter);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedVoter);
    }

    @GetMapping
    public ResponseEntity<List<Voter>> getAllVoters() {

        return ResponseEntity.ok(voterRepository.findAll());

    }
    
    @GetMapping("/{voterId}")
    public ResponseEntity<Voter> getVoterByVoterId(@PathVariable String voterId) {

        Voter voter = voterRepository.findByVoterId(voterId)
                .orElse(null);

        if (voter == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(voter);
    }
}