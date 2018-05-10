package com.yan.crawler.persist.dao;

import com.yan.crawler.data.UserTrack;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YZT on 2018/5/9.
 */
@Repository
public interface UserTrackDao {
    public int selectAll();
    public UserTrack select(int index);
    public List<UserTrack> selectUserTrack(int userID);
}
