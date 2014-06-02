package com.cinsec.dmc.exception;

public class BizException extends Exception {

    private static final long serialVersionUID = -1770001738002684831L;

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message + (cause != null ? " " + cause.getMessage() : ""), cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

}
