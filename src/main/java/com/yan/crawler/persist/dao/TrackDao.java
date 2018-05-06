package com.yan.crawler.persist.dao;

import com.yan.crawler.data.Track;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YZT on 2018/5/2.
 */
@Repository
public interface TrackDao {
    void insert(@Param("list") List<Track> tracks);
    void updateTag(String artist, String name, String tags);
    List<Track> getTrack(int index);
    void insertUserTrack(String username,@Param("tracks")List<Track> tracks);
    int count();
}
