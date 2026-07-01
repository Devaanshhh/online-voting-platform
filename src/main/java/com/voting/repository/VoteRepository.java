package com.voting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting.entity.Vote;
import com.voting.entity.Candidate;
import com.voting.entity.Voter;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByVoter(Voter voter);

    List<Vote> findByCandidate(Candidate candidate);

}