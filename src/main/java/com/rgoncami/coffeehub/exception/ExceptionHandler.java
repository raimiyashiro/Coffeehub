package com.rgoncami.coffeehub.exception;

import com.rgoncami.coffeehub.exception.exceptions.RoomCreationException;
import com.rgoncami.coffeehub.exception.exceptions.UserCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserCreationException.class})
    public ResponseEntity<ApiException> handleUserCreationException(
            UserCreationException e) {

        ApiException apiException = new ApiException(
                e.getCode(),
                e.getMessage(),
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {RoomCreationException.class})
    public ResponseEntity<ApiException> handleRoomCreationException(
            RoomCreationException e
    ) {
        ApiException apiException = new ApiException(
                e.getCode(),
                e.getMessage(),
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
