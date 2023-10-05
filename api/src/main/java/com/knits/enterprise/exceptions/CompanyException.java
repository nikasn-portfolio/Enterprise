package com.knits.enterprise.exceptions;

public class CompanyException extends AppException {

    public CompanyException(String message){
        super(message);
    }

    public CompanyException(Exception e){
        super(e);
    }
}
