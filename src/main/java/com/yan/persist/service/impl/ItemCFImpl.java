package com.yan.persist.service.impl;

import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;
import com.yan.hadoop.ItemCF.ItemCFJobRunner;
import com.yan.persist.service.ItemCF;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by YZT on 2018/5/10.
 */
@Service
public class ItemCFImpl implements ItemCF {

    @Override
    public List<Track> getUserCF(User2 user2) throws IOException {
        //找到user2的信息
        List<Track> tracks = ItemCFJobRunner.getRecommend(user2);
        //返回该用户的推荐列表
        return tracks;
    }
}
