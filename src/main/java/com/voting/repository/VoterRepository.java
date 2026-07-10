package com.voting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long> {

    Optional<Voter> findByVoterId(String voterId);

    boolean existsByVoterId(String voterId);

    boolean existsByEmail(String email);

}