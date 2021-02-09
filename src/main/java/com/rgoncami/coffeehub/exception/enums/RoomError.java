package com.rgoncami.coffeehub.exception.enums;

import lombok.Getter;

public enum RoomError {

    ROOM_NAME_IS_UNAVAILABLE("ROOM0001",
            "Choose another name for your room."
    ),
    ROOM_DOES_NOT_EXIST("ROOM0002",
            "Could not find a room for the given name.");

    @Getter
    private String code;
    @Getter
    private String message;

    RoomError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
