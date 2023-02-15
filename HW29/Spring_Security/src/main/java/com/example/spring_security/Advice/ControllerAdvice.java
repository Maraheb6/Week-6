package com.example.spring_security.Advice;

import com.example.spring_security.ApiException.ApiException;
import com.example.spring_security.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e){
        String message=e.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity ApiException(MethodArgumentNotValidException e){
        String message=e.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public  ResponseEntity ApiException(SQLIntegrityConstraintViolationException e){
        String message=e.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    // Method not allowed Exception
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    // Json parse Exception
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    // TypesMisMatch Exception
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }
}
