package com.yan.persist.service;

import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;

import java.io.IOException;
import java.util.List;

/**
 * Created by YZT on 2018/5/9.
 */
public interface UserCF {
    public List<Track> getUserCF(User2 user2) throws IOException;
}
