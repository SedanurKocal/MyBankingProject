package com.example.accountservice.core.exception.problemDetails;

public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails(){
        setTitle("Business Rule Violation");
        setType("");
        setStatus("400");
    }
}
