package com.dienmayhoangtien_service.exception;

public class BusinessException extends RuntimeException{


    private Integer status;
    private String message;
    public BusinessException(String msg) {
        super(msg);
        this.message = msg;
    }

    public BusinessException(String msg, Throwable ex) {
        super(msg, ex);
        this.message = msg;
    }

    public BusinessException(Integer status, String msg, Throwable ex) {
        super(msg, ex);
        this.status = status;
        this.message = msg;
    }


    public BusinessException(Integer status, String message) {
        super(String.valueOf(status));
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
