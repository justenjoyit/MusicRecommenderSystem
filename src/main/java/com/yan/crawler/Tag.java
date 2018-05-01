package com.yan.crawler;

/**
 * Created by YZT on 2018/5/1.
 */
public class Tag {
    private int count;
    private String name;
    private String url;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
