package com.paypal.bfs.test.employeeserv.exceptions;

public class InvalidDateFormatException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public InvalidDateFormatException() {
    }

    public InvalidDateFormatException(String message) {
        super(message);
    }

    public InvalidDateFormatException(Throwable cause) {
        super(cause);
    }
 
    public InvalidDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
