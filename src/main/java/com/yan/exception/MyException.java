package com.yan.exception;

/**
 * Created by YZT on 2018/4/18.
 */
public class MyException extends RuntimeException {

   private String code;

    public MyException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
