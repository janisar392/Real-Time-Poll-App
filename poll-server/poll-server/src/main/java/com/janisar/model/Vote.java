package com.janisar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "votes")
public class Vote {

    @Id
    private String id;

    private String pollId;

    private String optionId;

    private String voterToken;

    private String ipAddress;

    private Instant createdAt = Instant.now();

}
