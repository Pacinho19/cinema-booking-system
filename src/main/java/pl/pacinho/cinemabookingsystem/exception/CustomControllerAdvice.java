package pl.pacinho.cinemabookingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.pacinho.cinemabookingsystem.exception.model.ConflictException;
import pl.pacinho.cinemabookingsystem.exception.model.IllegalSeatPositionException;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(IllegalSeatPositionException.class)
    public ResponseEntity<String> handleIllegalSeatPositionException(IllegalSeatPositionException e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflictException(ConflictException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
