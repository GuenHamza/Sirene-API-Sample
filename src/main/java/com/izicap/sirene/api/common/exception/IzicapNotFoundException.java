package com.izicap.sirene.api.common.exception;

import org.springframework.http.HttpStatus;

public class IzicapNotFoundException extends IzicapException{

    public IzicapNotFoundException(String messageKey) {
        super(messageKey);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
