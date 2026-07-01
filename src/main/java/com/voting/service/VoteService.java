package com.voting.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voting.entity.Candidate;
import com.voting.entity.Vote;
import com.voting.entity.Voter;
import com.voting.repository.CandidateRepository;
import com.voting.repository.VoteRepository;
import com.voting.repository.VoterRepository;
import com.voting.exception.ResourceNotFoundException;
@Service
public class VoteService {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Transactional
    public String castVote(Long voterId, Long candidateId) {

    	Voter voter = voterRepository.findById(voterId)
    	        .orElseThrow(() -> new ResourceNotFoundException("Voter not found"));

        if (voter.isVoted()) {
            return "You have already voted";
        }

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        candidate.setVotes(candidate.getVotes() + 1);
        voter.setVoted(true);

        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setCandidate(candidate);
        vote.setVoteTime(LocalDateTime.now());

        candidateRepository.save(candidate);
        voterRepository.save(voter);
        voteRepository.save(vote);

        return "Vote cast successfully";
    }

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public Vote getVoteById(Long id) {
        return voteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vote not found"));
    }

    public List<Vote> getVotesByVoter(Long voterId) {

    	Voter voter = voterRepository.findById(voterId)
    	        .orElseThrow(() -> new ResourceNotFoundException("Voter not found"));

        return voteRepository.findByVoter(voter);
    }

    public List<Vote> getVotesByCandidate(Long candidateId) {

    	Candidate candidate = candidateRepository.findById(candidateId)
    	        .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        return voteRepository.findByCandidate(candidate);
    }
}