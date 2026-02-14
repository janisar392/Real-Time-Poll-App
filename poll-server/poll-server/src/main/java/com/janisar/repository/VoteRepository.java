package com.janisar.repository;

import com.janisar.model.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends MongoRepository<Vote, String> {

    Optional<Vote> findByPollIdAndVoterToken(String pollId, String voterToken);

    Optional<Vote> findByPollIdAndIpAddress(String pollId, String ipAddress);

}
