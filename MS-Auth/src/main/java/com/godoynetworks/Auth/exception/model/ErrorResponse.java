package com.godoynetworks.Auth.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private int status;
    private List<String> details;
}
