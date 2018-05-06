package com.yan.crawler.persist.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.yan.crawler.data.Tag;
import com.yan.crawler.data.Track;
import com.yan.crawler.persist.dao.TagDao;
import com.yan.crawler.persist.dao.TrackDao;
import com.yan.crawler.persist.service.MyDataHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZT on 2018/5/6.
 */
@Service
public class MyDataHandlingImpl implements MyDataHandling {

    @Autowired
    private TagDao tagDao;
    @Autowired
    private TrackDao trackDao;

    /**
     * 批量更新tag
     */
    @Override
    public void autoAddTag() {
        //获取数据库中所有tag数据
        List<Tag> tags = tagDao.selectAll();
        //获取数据库中所有track数据
        //每次处理20条数据
        int count = trackDao.count();
        for (int i = 0; i < count; i += 10) {
            List<Track> trackList = trackDao.getTrack(i);
            List<Track> tracks = new ArrayList<>();
            List<Track> toDelete = new ArrayList<>();
            //根据tag的数量处理track的tags字段
            for (Track track : trackList) {
                List<Tag> trackTags = JSONArray.parseArray(track.getTags(), Tag.class);
                //当tag值小于3个时，将该数据从数据库中删除
                if (trackTags == null || trackTags.size() < 10) {
                    toDelete.add(track);
                } else {
                    //去掉重复名字的tag
                    List<Tag> newTags = new ArrayList<>(tags);
                    newTags.removeAll(trackTags);
                    newTags.addAll(trackTags);
                    //更新track中的tag
                    track.setTags(JSONArray.toJSONString(newTags));
                    tracks.add(track);
                }
            }
            //更新到数据库
            if (tracks.size() != 0)
                trackDao.updateTrackTag(tracks);
            //将无用数据删除
            if (toDelete.size() != 0) {
                trackDao.deleteTrack(toDelete);
                trackDao.deleteUserTrack(toDelete);
            }
        }

    }
}
