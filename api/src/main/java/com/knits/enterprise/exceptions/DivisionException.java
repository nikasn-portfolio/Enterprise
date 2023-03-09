package com.knits.enterprise.exceptions;


public class DivisionException extends AppException {




   public DivisionException(String message) {
       super(message);
   }


   public DivisionException(Exception e) {
       super(e);
   }
}
