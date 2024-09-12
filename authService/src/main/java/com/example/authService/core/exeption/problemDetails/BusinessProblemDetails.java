package com.example.authService.core.exeption.problemDetails;

public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails(){
        setTitle("Business Rule Violation");
        setType("");
        setStatus("400");
    }
}
