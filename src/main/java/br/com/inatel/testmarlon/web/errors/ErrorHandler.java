package br.com.inatel.testmarlon.web.errors;


import br.com.inatel.testmarlon.TesteInatelApplication;
import br.com.inatel.testmarlon.core.errors.NonexistentStockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(TesteInatelApplication.class);

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentExcetion(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        logger.error(String.format("Error: %s", errorResponse));
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = NonexistentStockException.class)
    public ResponseEntity<ErrorResponse> NonExistentStock(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        logger.error(String.format("Error: %s", errorResponse));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
