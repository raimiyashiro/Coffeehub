package com.rgoncami.coffeehub.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ApiException {

    private final String code;
    private final String message;
    private final ZonedDateTime zonedDateTime;

}
