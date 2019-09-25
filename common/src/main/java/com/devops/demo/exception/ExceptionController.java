package com.devops.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author DatVM2
 */
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ResponseModal> handleBadRequest(GenericException ex) {
        ResponseModal error = new ResponseModal(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(error);
    }
}
