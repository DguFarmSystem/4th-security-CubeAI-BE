package com.cubeai.global.error.exception;

import com.cubeai.global.error.ErrorCode;

public class IllegalArgumentException extends BusinessException {
    public IllegalArgumentException() {
        super(ErrorCode.BAD_REQUEST);
    }
    public IllegalArgumentException(ErrorCode errorCode) {super(errorCode);}
}
