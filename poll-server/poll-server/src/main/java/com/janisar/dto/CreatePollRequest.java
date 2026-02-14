package com.janisar.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreatePollRequest {

    private String question;
    private List<String> options;

}
