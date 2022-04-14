package com.izicap.sirene.api.common;

import lombok.Data;

@Data
public class ErrorDto {

    private int code;

    private String message;

    private String additionalData;

    public ErrorDto(String message, int code) {
        super();
        this.message = message;
        this.code = code;
    }

    public ErrorDto(String message, String additionalData) {
        super();
        this.message = message;
        this.additionalData = additionalData;
    }

    public ErrorDto() {
    }

}