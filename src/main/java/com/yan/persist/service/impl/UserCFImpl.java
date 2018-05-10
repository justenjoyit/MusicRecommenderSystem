package com.yan.persist.service.impl;

import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;
import com.yan.hadoop.UserCF.JobRunner;
import com.yan.persist.service.UserCF;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by YZT on 2018/5/9.
 */
@Service
public class UserCFImpl implements UserCF {

    @Override
    public List<Track> getUserCF(User2 user2) throws IOException {
        //找到user2的信息
        List<Track> tracks = JobRunner.getRecommend(user2);
        //返回该用户的推荐列表
        return tracks;
    }
}
