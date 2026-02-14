package com.janisar.controller;

import com.janisar.model.Option;
import com.janisar.model.Poll;
import com.janisar.service.PollService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.janisar.dto.CreatePollRequest;
import com.janisar.dto.VoteRequest;


import java.util.List;

@RestController
@RequestMapping("/api/polls")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PollController {

    private final PollService pollService;

    // Create poll
    @PostMapping
    public Poll createPoll(@RequestBody CreatePollRequest request) {
        return pollService.createPoll(request.getQuestion(), request.getOptions());
    }

    // Get poll by ID
    @GetMapping("/{id}")
    public Poll getPoll(@PathVariable String id) {
        return pollService.getPollById(id);
    }

    // Vote
    @PostMapping("/{id}/vote")
    public Poll vote(
            @PathVariable String id,
            @RequestBody VoteRequest request,
            HttpServletRequest httpRequest
    ) {

        String ipAddress = httpRequest.getRemoteAddr();

        return pollService.vote(
                id,
                request.getOptionId(),
                request.getVoterToken(),
                ipAddress
        );
    }

    // Get results
    @GetMapping("/{id}/results")
    public List<Option> getResults(@PathVariable String id) {
        return pollService.getResults(id);
    }

}
