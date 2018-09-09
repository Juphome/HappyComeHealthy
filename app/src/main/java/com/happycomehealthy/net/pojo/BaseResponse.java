package com.happycomehealthy.net.pojo;

/**
 * creator: ZZF
 * careate date: 2017/10/25  08:40.
 */

public class BaseResponse<T> {

    /**
     * result : {"time":1509507169800}
     * resutlMessage : success
     * retCode : 0000
     */

    private T data;
    private String message;
    private String code;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
