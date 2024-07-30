package com.godoynetworks.Auth.exception.handler;

import com.godoynetworks.Auth.exception.ResourceNotFoundException;
import com.godoynetworks.Auth.exception.ValidationException;
import com.godoynetworks.Auth.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException (ValidationException exception) {
        ErrorResponse error = new ErrorResponse("Validation error", exception.getStatus(), exception.getErrors());
        return new ResponseEntity<>(error, HttpStatus.valueOf(exception.getStatus()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException (ResourceNotFoundException exception) {
        ErrorResponse error = new ErrorResponse("Resource not found", HttpStatus.NOT_FOUND.value(), List.of(exception.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception) {
        ErrorResponse error = new ErrorResponse("Method not supported", HttpStatus.METHOD_NOT_ALLOWED.value(),
                List.of(String.format("Method %s is not supported. Try %s",
                        exception.getMethod(),
                        exception.getSupportedHttpMethods()))
        );
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException (BadCredentialsException exception) {
        ErrorResponse error = new ErrorResponse("Invalid credentials", HttpStatus.UNAUTHORIZED.value(), List.of(exception.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    // Not working apparently, fix later
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException (AccessDeniedException exception) {
        ErrorResponse error = new ErrorResponse("Access denied", HttpStatus.FORBIDDEN.value(), List.of(exception.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
//        ErrorResponse error = new ErrorResponse(
//                "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of("An unexpected error occurred. Please try again later.")
//        );
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    /**
     * Como estourar uma resourceNotFound:
     * #EXEMPLO:
     * User user = userRepository.findById(id)
     *     .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
     */

}
