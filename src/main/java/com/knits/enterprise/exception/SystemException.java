package com.knits.enterprise.exception;

public class SystemException extends AppException{

    public SystemException(String message){
        super(message);
    }

    public SystemException(Exception e){
        super(e);
    }
}
