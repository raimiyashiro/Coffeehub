package com.rgoncami.coffeehub.exception.exceptions;

import com.rgoncami.coffeehub.exception.enums.RoomError;
import lombok.Getter;

public class RoomCreationException extends RuntimeException {

    @Getter
    private final String code;

    public RoomCreationException(RoomError e) {
        super(e.getMessage());
        this.code = e.getCode();
    }
}
