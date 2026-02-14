package com.janisar.service;

import com.janisar.model.Option;
import com.janisar.model.Poll;
import com.janisar.model.Vote;
import com.janisar.repository.PollRepository;
import com.janisar.repository.VoteRepository;
import com.janisar.websocket.PollEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;
    private final PollEventPublisher pollEventPublisher;

    // Create new poll
    public Poll createPoll(String question, List<String> optionTexts) {

        if (question == null || question.isBlank()) {
            throw new RuntimeException("Question cannot be empty");
        }

        if (optionTexts == null || optionTexts.size() < 2) {
            throw new RuntimeException("At least two options required");
        }

        List<Option> options = optionTexts.stream()
                .map(text -> new Option(UUID.randomUUID().toString(), text, 0))
                .toList();

        Poll poll = new Poll();
        poll.setQuestion(question);
        poll.setOptions(options);
        poll.setCreatedAt(Instant.now());

        return pollRepository.save(poll);
    }

    // Get poll by ID
    public Poll getPollById(String pollId) {
        return pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));
    }

    // Vote logic with real-time broadcast
    public Poll vote(String pollId, String optionId, String voterToken, String ipAddress) {

        Poll poll = getPollById(pollId);

        // Check option exists
        Optional<Option> selectedOption = poll.getOptions()
                .stream()
                .filter(opt -> opt.getId().equals(optionId))
                .findFirst();

        if (selectedOption.isEmpty()) {
            throw new RuntimeException("Option not found");
        }

        // Check duplicate vote by token
        if (voteRepository.findByPollIdAndVoterToken(pollId, voterToken).isPresent()) {
            throw new RuntimeException("You have already voted (token)");
        }

        // Check duplicate vote by IP
        if (voteRepository.findByPollIdAndIpAddress(pollId, ipAddress).isPresent()) {
            throw new RuntimeException("You have already voted (IP)");
        }

        // Save vote record
        Vote vote = new Vote();
        vote.setPollId(pollId);
        vote.setOptionId(optionId);
        vote.setVoterToken(voterToken);
        vote.setIpAddress(ipAddress);
        vote.setCreatedAt(Instant.now());

        voteRepository.save(vote);

        // Increment vote count
        selectedOption.get().setVotes(selectedOption.get().getVotes() + 1);

        // Save updated poll
        Poll updatedPoll = pollRepository.save(poll);

        // Broadcast real-time update
        pollEventPublisher.broadcastPollUpdate(updatedPoll);

        return updatedPoll;
    }

    // Get results
    public List<Option> getResults(String pollId) {
        Poll poll = getPollById(pollId);
        return poll.getOptions();
    }
}
