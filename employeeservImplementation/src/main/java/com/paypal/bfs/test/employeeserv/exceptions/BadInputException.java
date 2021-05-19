package com.paypal.bfs.test.employeeserv.exceptions;

public class BadInputException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    
    public BadInputException() {
    }

    public BadInputException(String message) {
        super(message);
    }

    public BadInputException(Throwable cause) {
        super(cause);
    }

    public BadInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
