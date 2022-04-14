package com.izicap.sirene.api.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Parent of all izicap exceptions.
 * All exceptions inheriting from this exception will be Runtime Exceptions
 *
 */

public abstract class IzicapException extends RuntimeException {

    public IzicapException(String message) {
        super(message);
    }

    public IzicapException(String message, Throwable cause) {
        super(message, cause);
    }

    private static final long serialVersionUID = 1L;

    public abstract HttpStatus getHttpStatus();
}
