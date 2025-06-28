package com.humansofulmu.common.resonse;

public class ResultVO<T> {

    private int status;
    private String message;
    private T data;

    public ResultVO(SuccessCode successCode) {
        this.status = successCode.getStatus();
        this.message = successCode.getMessage();
        this.data = null;
    }

    public ResultVO(SuccessCode successCode, T data) {
        this.status = successCode.getStatus();
        this.message = successCode.getMessage();
        this.data = data;
    }

    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}