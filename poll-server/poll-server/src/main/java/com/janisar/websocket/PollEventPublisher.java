package com.janisar.websocket;

import com.janisar.model.Poll;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PollEventPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public void broadcastPollUpdate(Poll poll) {

        String destination = "/topic/poll/" + poll.getId();

        messagingTemplate.convertAndSend(destination, poll);
    }
}
