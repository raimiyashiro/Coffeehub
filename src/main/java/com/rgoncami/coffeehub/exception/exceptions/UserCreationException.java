package com.rgoncami.coffeehub.exception.exceptions;

import com.rgoncami.coffeehub.exception.enums.UserError;
import lombok.Getter;

public class UserCreationException extends RuntimeException {

    @Getter
    private final String code;

    public UserCreationException(UserError u) {
        super(u.getMessage());
        this.code = u.getCode();
    }
}
