package fi.tuni.compse140.exercise1.exception;

import fi.tuni.compse140.exercise1.api.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UnavailableHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ExternalAPIException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        ErrorDTO error = new ErrorDTO(ex.getMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

}
