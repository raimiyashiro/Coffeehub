package com.rgoncami.coffeehub.exception.enums;

import lombok.Getter;

public enum UserError {

    USER_NICKNAME_ALREADY_EXISTS("USER0001",
            "This nickname is not available. Please, choose another one."
    ),
    USER_DOES_NOT_EXIST("USER0002",
            "Could not find a User for the given nickname.");

    @Getter
    private String code;
    @Getter
    private String message;

    UserError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
