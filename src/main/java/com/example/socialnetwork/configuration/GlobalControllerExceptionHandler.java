package com.example.socialnetwork.configuration;

import com.example.socialnetwork.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({
            CantParseJsonToEntityException.class,
            EmptyUserIdForSearchException.class,
            IncorrectRequestException.class,
            IncorrectUserIdException.class,
            EmptyRequiredFieldException.class,
            UserWithLoginAlreadyExistsException.class
    })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleLimitations(RuntimeException ex) {
        log.warn("handleLimitations", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(
            {
                    UserNotFoundException.class
            })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEntityNotFound(RuntimeException ex) {
        log.warn("handleEntityNotFound", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

