package com.izicap.sirene.api.common.exception;

import org.springframework.http.HttpStatus;

public class IzicapBadRequestException extends IzicapException{

    public IzicapBadRequestException(String messageKey) {
        super(messageKey);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
