package com.rgoncami.coffeehub.exception;

import com.rgoncami.coffeehub.exception.exceptions.RoomCreationException;
import com.rgoncami.coffeehub.exception.exceptions.RoomNotFoundException;
import com.rgoncami.coffeehub.exception.exceptions.UserCreationException;
import com.rgoncami.coffeehub.exception.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserCreationException.class})
    public ResponseEntity<ApiException> handleUserCreationException(UserCreationException e) {
        ApiException apiException = ApiException.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .zonedDateTime(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {RoomCreationException.class})
    public ResponseEntity<ApiException> handleRoomCreationException(RoomCreationException e) {
        ApiException apiException = ApiException.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .zonedDateTime(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ApiException> handleUserNotFoundException(UserNotFoundException e) {
        ApiException apiException = ApiException.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .zonedDateTime(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {RoomNotFoundException.class})
    public ResponseEntity<ApiException> handleRoomNotFoundException(RoomNotFoundException e) {
        ApiException apiException = ApiException.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .zonedDateTime(ZonedDateTime.now())
                .build();

        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}
