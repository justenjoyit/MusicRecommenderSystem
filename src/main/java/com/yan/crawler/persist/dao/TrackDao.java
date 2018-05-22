package com.yan.crawler.persist.dao;

import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZT on 2018/5/2.
 */
@Repository
public interface TrackDao {
    void insert(List<Track> tracks);

    void updateTag(String artist, String name, String tags);

    List<Track> getTrack(int index);

    void insertUserTrack(User2 user2, @Param("tracks") List<Track> tracks);

    int count();

    void updateTrackTag(@Param("tracks") List<Track> tracks);

    void deleteTrack(@Param("tracks") List<Track> tracks);

    void deleteUserTrack(@Param("tracks") List<Track> tracks);

    ArrayList<Track> select(@Param("tracks") List<Track> tracks);

    Track selectTrack(int id);
}
