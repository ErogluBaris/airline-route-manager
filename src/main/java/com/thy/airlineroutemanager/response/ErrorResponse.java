package com.thy.airlineroutemanager.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private int status;
    private String error;
    private String message;
    private Object details;
}