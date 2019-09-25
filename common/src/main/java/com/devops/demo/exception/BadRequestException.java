package com.devops.demo.exception;

/**
 * @author DatVM2
 */
public class BadRequestException extends GenericException {
    private static final long serialVersionUID = 1L;

    /**
     * Instance with message
     * @param message
     */
    public BadRequestException(String message) {
        super(message);
        this.status = 400;
    }

    public BadRequestException() {
        super("Bad Request");
        this.status = 400;
    }

    /**
     * Instance with code and message
     * @param code
     * @param message
     */
    public BadRequestException(String code, String message) {
        super(code, message, 400);
    }
}
