package ericholbert.springbootexercise.artwork.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ArtworkNotFoundException.class)
    ResponseEntity<ErrorBody> artworkNotFoundHandler(ArtworkNotFoundException e, HttpServletRequest request) {
        return createResponse(LocalDateTime.now(), "Artwork Not Found", e.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingParametersException.class)
    ResponseEntity<ErrorBody> missingParametersHandler(MissingParametersException e, HttpServletRequest request) {
        return createResponse(LocalDateTime.now(), "Missing Parameters", e.getMessage(), request.getRequestURI(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSortPropertyException.class)
    ResponseEntity<ErrorBody> invalidSortPropertyHandler(InvalidSortPropertyException e, HttpServletRequest request) {
        return createResponse(LocalDateTime.now(), "Invalid Sort Property", e.getMessage(), request.getRequestURI(), HttpStatus.BAD_REQUEST);
    }

    private record ErrorBody(LocalDateTime timestamp, int status, String error, String detail, String path) {
    }

    private ResponseEntity<ErrorBody> createResponse(LocalDateTime timestamp, String error, String detail, String path, HttpStatus status) {
        return new ResponseEntity<>(new ErrorBody(timestamp, status.value(), error, detail, path), status);
    }
}
