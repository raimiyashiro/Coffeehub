package com.rgoncami.coffeehub.exception.exceptions;

import com.rgoncami.coffeehub.exception.enums.UserError;
import lombok.Getter;

public class UserNotFoundException extends RuntimeException {

    @Getter
    private final String code;

    public UserNotFoundException(UserError e) {
        super(e.getMessage());
        this.code = e.getCode();
    }
}
