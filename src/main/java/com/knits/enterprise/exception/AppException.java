package com.knits.enterprise.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
 @EqualsAndHashCode(callSuper=false)
public class AppException extends RuntimeException{

    private int code;

    public AppException(String message){
        super(message);
    }

    public AppException(Exception e){
        super(e);
    }
}
