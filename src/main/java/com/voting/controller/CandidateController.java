package com.voting.controller;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.voting.entity.Candidate;
import com.voting.repository.CandidateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping
    public ResponseEntity<Candidate> addCandidate(@Valid @RequestBody Candidate candidate) {

        Candidate saved = candidateRepository.save(candidate);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {

        candidateRepository.deleteById(id);

        return ResponseEntity.ok("Candidate Deleted Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id,
                                                     @RequestBody Candidate updatedCandidate) {

        Candidate candidate = candidateRepository.findById(id)
                .orElse(null);

        if (candidate == null) {
            return ResponseEntity.notFound().build();
        }

        candidate.setCandidateName(updatedCandidate.getCandidateName());
        candidate.setParty(updatedCandidate.getParty());

        Candidate savedCandidate = candidateRepository.save(candidate);

        return ResponseEntity.ok(savedCandidate);
    }
}