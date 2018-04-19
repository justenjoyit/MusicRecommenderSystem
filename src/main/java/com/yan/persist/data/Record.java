package com.yan.persist.data;

import java.util.Date;

/**
 * Created by YZT on 2018/4/19.
 */
public class Record {
    private String className;
    private String time;
    private long millis;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    @Override
    public String toString() {
        return "Record{" +
                "className='" + className + '\'' +
                ", time=" + time +
                ", millis=" + millis +
                '}';
    }
}
