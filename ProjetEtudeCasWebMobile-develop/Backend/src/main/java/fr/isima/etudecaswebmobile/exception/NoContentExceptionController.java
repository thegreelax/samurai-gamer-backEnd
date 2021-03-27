package fr.isima.etudecaswebmobile.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoContentExceptionController {
    @ExceptionHandler(value = NoContentException.class)
    public ResponseEntity<Object> exception(NoContentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NO_CONTENT);
    }
}
