package com.zxsoft.demo2.sys;

public class FanFExceptionDTO {
    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public FanFExceptionDTO() {
    }

    public FanFExceptionDTO(int status, String message) {

        this.status = status;
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
