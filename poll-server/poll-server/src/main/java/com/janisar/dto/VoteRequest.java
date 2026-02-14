package com.janisar.dto;

import lombok.Data;

@Data
public class VoteRequest {

    private String optionId;
    private String voterToken;

}
