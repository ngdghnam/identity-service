package com.devteria.identity_service.exception;

import com.devteria.identity_service.dto.requests.ApiRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiRespone> handleRuntimeException(RuntimeException e) {

        ApiRespone apiRespone = new ApiRespone();

        apiRespone.setCode(ErrorCode.UNCATEGORIZED_EXISTED.getCode());
        apiRespone.setMessage(ErrorCode.UNCATEGORIZED_EXISTED.getMessage());

        return ResponseEntity.badRequest().body(apiRespone);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiRespone> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();

        ApiRespone apiRespone = new ApiRespone();

        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiRespone);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespone> handleValidation(MethodArgumentNotValidException e) {

        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ex) {
            //throw new RuntimeException(ex);
        }

        ApiRespone apiRespone = new ApiRespone();
        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiRespone);
    }
}
