package com.izicap.sirene.api.common.exception;

import org.springframework.http.HttpStatus;

public class IzicapInternalErrorException extends IzicapException{

    public IzicapInternalErrorException(String messageKey) {
        super(messageKey);
    }

    public IzicapInternalErrorException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
