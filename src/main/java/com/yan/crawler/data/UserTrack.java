package com.yan.crawler.data;

/**
 * Created by YZT on 2018/5/9.
 */
public class UserTrack {
    private int id;
    private int userID;
    private String username;
    private int trackID;
    private String artist;
    private String trackname;
    private int duration;
    private double recommend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTrackname() {
        return trackname;
    }

    public void setTrackname(String trackname) {
        this.trackname = trackname;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRecommend() {
        return recommend;
    }

    public void setRecommend(double recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        return "UserTrack{" +
                "id=" + id +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", trackID=" + trackID +
                ", artist='" + artist + '\'' +
                ", trackname='" + trackname + '\'' +
                ", duration=" + duration +
                '}';
    }
}
