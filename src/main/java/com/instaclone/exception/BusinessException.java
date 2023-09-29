package com.instaclone.exception;

public class BusinessException extends RuntimeException{


    private String errorCode;
    private String message;
    public BusinessException(String msg) {
        super(msg);
        this.message = msg;
    }

    public BusinessException(String msg, Throwable ex) {
        super(msg, ex);
        this.message = msg;
    }

    public BusinessException(String errorCode, String msg, Throwable ex) {
        super(msg, ex);
        this.errorCode = errorCode;
        this.message = msg;
    }


    public BusinessException(String errCode, String message) {
        super(errCode);
        this.errorCode = errCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
