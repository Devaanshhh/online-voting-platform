package com.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.voting.entity.Candidate;
import com.voting.repository.CandidateRepository;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public String deleteCandidate(@PathVariable Long id) {

        candidateRepository.deleteById(id);

        return "Candidate Deleted Successfully";
    }
    @PutMapping("/{id}")
    public Candidate updateCandidate(@PathVariable Long id,
                                     @RequestBody Candidate updatedCandidate) {

        Candidate candidate = candidateRepository.findById(id)
                .orElse(null);

        if (candidate == null) {
            return null;
        }

        candidate.setCandidateName(updatedCandidate.getCandidateName());
        candidate.setParty(updatedCandidate.getParty());

        return candidateRepository.save(candidate);
    }
    
}