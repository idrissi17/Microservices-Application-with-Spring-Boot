package org.example.loansservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String loan, String mobileNumber, String mobileNumber1) {
        super(String.format("%s not found with %s : '%s'", loan, mobileNumber, mobileNumber1));
    }
}