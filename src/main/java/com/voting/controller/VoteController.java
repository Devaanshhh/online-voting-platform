package com.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.voting.entity.Vote;
import com.voting.service.VoteService;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/{voterId}/{candidateId}")
    public ResponseEntity<String> castVote(@PathVariable Long voterId,
                                           @PathVariable Long candidateId) {

        return ResponseEntity.ok(voteService.castVote(voterId, candidateId));
    }

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes() {

        return ResponseEntity.ok(voteService.getAllVotes());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVoteById(@PathVariable Long id) {

        return ResponseEntity.ok(voteService.getVoteById(id));

    }

    @GetMapping("/voter/{voterId}")
    public ResponseEntity<List<Vote>> getVotesByVoter(@PathVariable Long voterId) {

        return ResponseEntity.ok(voteService.getVotesByVoter(voterId));

    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<Vote>> getVotesByCandidate(@PathVariable Long candidateId) {

        return ResponseEntity.ok(voteService.getVotesByCandidate(candidateId));

    }
}