package com.example.transactionService.core.exception.problemDetails;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationProblemDetails extends ProblemDetails {
    public ValidationProblemDetails(){
        setTitle("Validation Rule Violation");
        setDetail("Validation Problem");
        setType("");
        setStatus("400");
    }

    private Map<String,String> errors;
}
