package com.rgoncami.coffeehub.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
public class ApiException {

    private final String code;
    private final String message;
    private final ZonedDateTime zonedDateTime;

}
