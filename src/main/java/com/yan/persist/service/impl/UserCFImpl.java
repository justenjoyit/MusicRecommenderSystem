package com.yan.persist.service.impl;

import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;
import com.yan.crawler.persist.dao.TrackDao;
import com.yan.hadoop.UserCF.UserCFJobRunner;
import com.yan.persist.service.UserCF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by YZT on 2018/5/9.
 */
@Service
public class UserCFImpl implements UserCF {

    @Autowired
    private TrackDao trackDao;

    @Override
    public List<Track> getUserCF(User2 user2) throws IOException {
        //找到user2的信息
        List<Track> tracks = UserCFJobRunner.getRecommend(user2);
        //返回该用户的推荐列表前100个
        Comparator<Track> comparator = new Comparator<Track>() {
            @Override
            public int compare(Track o1, Track o2) {
                if (o1.getRecommend() < o2.getRecommend())
                    return 1;
                else
                    return -1;
            }
        };
        Collections.sort(tracks,comparator);
        List<Track> trackList;
        if (tracks.size() > 100)
            trackList = tracks.subList(0, 100);
        else
            trackList = tracks;

        //从数据库中将值补充完整
        trackList = trackDao.select(trackList);
        return trackList;
    }
}
