package com.rgoncami.coffeehub.exception.exceptions;

import com.rgoncami.coffeehub.exception.enums.RoomError;
import lombok.Getter;

public class UserNotFoundException extends RuntimeException {

    @Getter
    private String code;

    public UserNotFoundException(RoomError e) {
        super(e.getMessage());
        this.code = e.getCode();
    }
}
