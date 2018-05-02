package com.yan.crawler;

import java.util.ArrayList;

/**
 * Created by YZT on 2018/5/1.
 */
public class Track {
    private int id;
    private String artist;
    private String name;
    private String mbid;
    private String url;
    private String images;
    private String tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    //    public ArrayList<String> getImages() {
//        return images;
//    }

//    public void setImages(ArrayList<String> images) {
//        this.images = images;
//    }
//
//    public void addImage(String image) {
//        if (images == null)
//            images = new ArrayList<>();
//        images.add(image);
//    }

    @Override
    public String toString() {
        return "Track{" +
                "artist='" + artist + '\'' +
                ", name='" + name + '\'' +
                ", mbid='" + mbid + '\'' +
                ", url='" + url + '\'' +
                ", images=" + images +
                '}';
    }
}
