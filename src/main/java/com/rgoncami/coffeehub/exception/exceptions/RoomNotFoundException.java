package com.rgoncami.coffeehub.exception.exceptions;

import com.rgoncami.coffeehub.exception.enums.RoomError;
import lombok.Getter;

public class RoomNotFoundException extends RuntimeException {

    @Getter
    private final String code;

    public RoomNotFoundException(RoomError e) {
        super(e.getMessage());
        this.code = e.getCode();
    }
}
