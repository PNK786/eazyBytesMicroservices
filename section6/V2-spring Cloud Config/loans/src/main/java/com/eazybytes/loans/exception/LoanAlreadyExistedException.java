package com.eazybytes.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistedException extends RuntimeException
{

    public LoanAlreadyExistedException(String message)
    {
        super(message);
    }
}
