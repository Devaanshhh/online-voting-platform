package com.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.voting.entity.Candidate;
import com.voting.entity.Voter;
import com.voting.repository.CandidateRepository;
import com.voting.repository.VoterRepository;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/{voterId}/{candidateId}")
    public String castVote(@PathVariable Long voterId,
                           @PathVariable Long candidateId) {

        Voter voter = voterRepository.findById(voterId).orElse(null);

        if (voter == null) {
            return "Voter not found";
        }

        if (voter.isVoted()) {
            return "You have already voted";
        }

        Candidate candidate = candidateRepository.findById(candidateId).orElse(null);

        if (candidate == null) {
            return "Candidate not found";
        }

        candidate.setVotes(candidate.getVotes() + 1);
        voter.setVoted(true);

        candidateRepository.save(candidate);
        voterRepository.save(voter);

        return "Vote cast successfully";
    }
}