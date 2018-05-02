package com.yan.crawler;

import java.util.ArrayList;

/**
 * Created by YZT on 2018/5/1.
 */
public class User2 {
    private String name;
    private String realname;
    private String images;
    private String url;
    private String country;
    private int age;
    private String gender;
    private String subscriber;
    private int playcount;
    private int playlists;
    private int bootstrap;
    private String registered;
    private String alumni;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public int getPlaylists() {
        return playlists;
    }

    public void setPlaylists(int playlists) {
        this.playlists = playlists;
    }

    public int getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(int bootstrap) {
        this.bootstrap = bootstrap;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getAlumni() {
        return alumni;
    }

    public void setAlumni(String alumni) {
        this.alumni = alumni;
    }

    @Override
    public String toString() {
        return "User2{" +
                "name='" + name + '\'' +
                ", realname='" + realname + '\'' +
                ", images='" + images + '\'' +
                ", url='" + url + '\'' +
                ", country='" + country + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", subscriber='" + subscriber + '\'' +
                ", playcount=" + playcount +
                ", playlists=" + playlists +
                ", bootstrap=" + bootstrap +
                ", registered='" + registered + '\'' +
                ", alumni='" + alumni + '\'' +
                '}';
    }
}
