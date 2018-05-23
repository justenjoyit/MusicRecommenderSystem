package com.yan.persist.entity;

import java.util.Date;

/**
 * Created by YZT on 2018/5/23.
 */
public class Verify {
    private int id;
    private String code;
    private Date created;
    private Date expired;
    private int status;
    private String target;
    private String verify_method;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getVerify_method() {
        return verify_method;
    }

    public void setVerify_method(String verify_method) {
        this.verify_method = verify_method;
    }
}
