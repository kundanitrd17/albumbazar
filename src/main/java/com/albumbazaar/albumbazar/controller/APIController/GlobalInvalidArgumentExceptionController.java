package com.albumbazaar.albumbazar.controller.APIController;

import com.albumbazaar.albumbazar.dto.ErrorDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalInvalidArgumentExceptionController {

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<?> ExceptionhandlerForInvalidFormData(MethodArgumentNotValidException exception) {

        final ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(exception.getBindingResult().getFieldError().getDefaultMessage());

        return ResponseEntity.unprocessableEntity().body(errorDTO);
    }

    @ExceptionHandler({ NumberFormatException.class })
    public ResponseEntity<?> ExceptionHandlerForInvalidNumberFormatType() {

        final ErrorDTO error = new ErrorDTO();
        error.setMessage("Invalid Type");

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }
}
