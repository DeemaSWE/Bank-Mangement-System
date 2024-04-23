package com.example.bankmanagementsystem.Advice;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.Api.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiResponse> ApiException(ApiException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> ConstraintViolationException(ConstraintViolationException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = TransactionSystemException.class)
    public ResponseEntity<ApiResponse> TransactionSystemException(TransactionSystemException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> NoResourceFoundException(NoResourceFoundException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> DataIntegrityViolationException(DataIntegrityViolationException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ApiResponse> NullPointerException(NullPointerException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponse> SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> HttpMessageNotReadableException(HttpMessageNotReadableException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    @ExceptionHandler(value = HttpMessageNotWritableException.class)
    public ResponseEntity<ApiResponse> HttpMessageNotWritableException(HttpMessageNotWritableException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }
}
