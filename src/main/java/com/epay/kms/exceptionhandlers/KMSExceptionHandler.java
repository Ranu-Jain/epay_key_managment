/*
 *
 *  Copyright (c) [2024] [State Bank of India]
 *  All rights reserved.
 *
 *  Author:@V0000001(Shilpa Kothre)
 *  Version:1.0
 *
 */


package com.epay.kms.exceptionhandlers;


import com.epay.kms.dto.ErrorDto;
import com.epay.kms.exception.KeyManagementException;
import com.epay.kms.model.response.KMSResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class KMSExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(KeyManagementException.class)
    public ResponseEntity<Object> handleSecurityException(KeyManagementException ex) {
        ErrorDto errorDto = ErrorDto.builder()
                .errorCode(ex.getErrorCode())
                .errorMessage(ex.getErrorMessage())
                .build();
        return generateResponseWithErrors(List.of(errorDto));
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI();
        logger.error("Error in handleGenericException ", ex);
        ErrorDto errorDto = ErrorDto.builder()
                .errorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .errorMessage(ex.getMessage())
                .build();
        return ResponseEntity.internalServerError().body(
                KMSResponse.builder()
                        .status(1)
                        .errors(List.of(errorDto))
                        .build());
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String requestUri = ((ServletWebRequest)request).getRequest().getRequestURI();
        logger.error("Error in handleConflict ", ex);
        ErrorDto errorDto = ErrorDto.builder()
                .errorCode(String.valueOf(HttpStatus.CONFLICT.value()))
                .errorMessage(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                KMSResponse.builder()
                        .status(1)
                        .errors(List.of(errorDto))
                        .build());
    }
    private ResponseEntity<Object> generateResponseWithErrors(List<ErrorDto> errors) {
        return ResponseEntity.ok().body(
                KMSResponse.builder()
                        .status(1)
                        .errors(errors)
                        .build());
    }


}