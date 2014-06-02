package com.cinsec.dmc.exception;

public class SysException extends RuntimeException {

    private static final long serialVersionUID = -6248213346226730883L;

    public SysException() {
        super();
    }

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

}
