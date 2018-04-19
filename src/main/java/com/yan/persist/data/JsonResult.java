package com.yan.persist.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZT on 2018/4/17.
 */
public class JsonResult {

    private String errorCode;   //0：通过 1：失败
    private String message;
    private List<? extends Object> data;

    public JsonResult() {
        this.errorCode = "1";
        this.data = new ArrayList<>();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<? extends Object> getData() {
        return data;
    }

    public void setData(List<? extends Object> data) {
        this.data = data;
    }

}
