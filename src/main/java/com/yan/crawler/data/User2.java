package com.yan.crawler.data;

import java.util.ArrayList;

/**
 * Created by YZT on 2018/5/1.
 */
public class User2 {
    private int id;
    private String name;
    private String realname;
    private String images;
    private String url;
    private String country;
    private int playcount;
    private String registered;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "User2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", realname='" + realname + '\'' +
                ", images='" + images + '\'' +
                ", url='" + url + '\'' +
                ", country='" + country + '\'' +
                ", playcount=" + playcount +
                ", registered='" + registered + '\'' +
                '}';
    }
}
