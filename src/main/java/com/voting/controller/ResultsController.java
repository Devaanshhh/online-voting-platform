package com.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting.entity.Candidate;
import com.voting.repository.CandidateRepository;
import com.voting.repository.VoterRepository;

@RestController
public class ResultsController {

    @Autowired
    private CandidateRepository candidateRepository;


    @GetMapping("/results")
    public List<Candidate> getResults() {
    	
        return candidateRepository.findAllByOrderByVotesDesc();

    }
    @GetMapping("/winner")
    public Candidate getWinner() {

        return candidateRepository
                .findAllByOrderByVotesDesc()
                .get(0);
    }
    @GetMapping("/totalVotes")
    public int totalVotes() {

        return candidateRepository.findAll()
                .stream()
                .mapToInt(Candidate::getVotes)
                .sum();
    }
    @GetMapping("/candidateCount")
    public long candidateCount() {

        return candidateRepository.count();
    }
    
    @Autowired
    private VoterRepository voterRepository;

    @GetMapping("/voterCount")
    public long voterCount() {
        return voterRepository.count();
    }
}