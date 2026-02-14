package com.janisar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "polls")
public class Poll {

    @Id
    private String id;

    private String question;

    private List<Option> options;

    private Instant createdAt = Instant.now();

}
