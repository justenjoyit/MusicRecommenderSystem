package com.yan.persist.service.impl;

import com.yan.crawler.data.UserTrack;
import com.yan.crawler.persist.dao.UserTrackDao;
import com.yan.hadoop.dao.HdfsDao;
import com.yan.persist.service.Score;
import com.yan.utils.Consts;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by YZT on 2018/5/9.
 */
@Service
public class ScoreImpl implements Score {
    @Autowired
    private UserTrackDao userTrackDao;
    @Autowired
    private HdfsDao hdfsDao;

    public void caculate() throws IOException, URISyntaxException {
        //新建一个文件
        //格式是userID,itemID,score
        File file = new File(Consts.DATA_ROOT_PATH + "recommend/input/ActionList.txt");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        //打开文件输出流
        FileOutputStream fop = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");

        //读取数据库下标
        int index = 0;
        int count = userTrackDao.selectAll();
        while (index < count - 1) {
            UserTrack userTrack = userTrackDao.select(index);
            int userID = userTrack.getUserID();
            List<UserTrack> userTracks = userTrackDao.selectUserTrack(userID);
            //计算评分
            int sumDuration = 0;
            for (UserTrack userTrack1 : userTracks) {
                if (userTrack1.getDuration() != 0)
                    sumDuration += userTrack1.getDuration();
            }
            for (UserTrack userTrack1 : userTracks) {
                if (userTrack1.getDuration() != 0) {
                    //计算duration在该用户总duration的占比为评分
                    userTrack1.setRecommend(((double) userTrack1.getDuration()) / ((double) sumDuration));
                    //写入到文件
                    String s = userTrack1.getUserID() + "," + userTrack1.getTrackID() + "," + userTrack1.getRecommend();
                    writer.append(s);
                    writer.append("\r\n");
                }
            }
            index += userTracks.size();
        }
        writer.close();
        fop.close();
        //上传文件到hdfs
        hdfsDao.upload("recommend/input/ActionList.txt", "/recommend/input/ActionList.txt");

    }

}
