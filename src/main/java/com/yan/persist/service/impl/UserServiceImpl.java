package com.yan.persist.service.impl;

import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;
import com.yan.crawler.data.UserTrack;
import com.yan.crawler.persist.dao.TrackDao;
import com.yan.crawler.persist.dao.User2Dao;
import com.yan.crawler.persist.dao.UserTrackDao;
import com.yan.exception.MyException;
import com.yan.persist.dao.UserDao;
import com.yan.persist.entity.User;
import com.yan.persist.service.UserService;
import com.yan.utils.Consts;
import com.yan.utils.MD5;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by YZT on 2018/4/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserTrackDao userTrackDao;
    @Autowired
    private User2Dao user2Dao;
    @Autowired
    private TrackDao trackDao;

    /**
     * 用户注册
     *
     * @param post
     * @return
     */
    @Override
    @Transactional
    public User register(User post) {
        //查找数据库是否有同username或email的用户
        User user = userDao.findUserByUsernameOrEmail(post);
        if (user != null) {
            throw new IllegalArgumentException("用户名或邮箱重复");
        }
        user = post;
        user.setStatus(0);
        user.setPassword(MD5.md5(user.getPassword()));
        userDao.save(user);

        return user;
    }

    /**
     * 更新状态
     * @param post
     */
    @Override
    @Transactional
    public void updateStatus(User post) {
        userDao.updateStatus(post);
    }

    /**
     * 删除用户
     * @param post
     */
    @Override
    @Transactional
    public void deleteUser(User post) {
        userDao.deleteUser(post);
    }

    /**
     * 根据name查找用户
     * @param name
     * @return
     */
    @Override
    @Transactional
    public User getUserByName(String name) {
        User user = new User();
        user.setUsername(name);
        return userDao.findUserByUsernameOrEmail(user);
    }

    /**
     * 修改密码
     * @param post
     */
    @Override
    @Transactional
    public void changePwd(User post) {
        User user = userDao.findUserByUsernameOrEmail(post);
        if (user == null)
            throw new MyException(Consts.USER_NOT_EXIST, "0");
        post.setPassword(MD5.md5(post.getPassword()));
        userDao.updatePwd(post);
    }

    /**
     * 获取所有用户信息
     * @return
     */
    @Override
    @Transactional
    public ArrayList<User> getAll() {
        return userDao.getAll();
    }

    /**
     * 上传文件
     * @param name
     * @param multipartFile
     * @throws IOException
     */
    @Override
    @Transactional
    public void upload(String name, MultipartFile multipartFile) throws IOException {
        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;
        DiskFileItem fileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
        InputStream is = fileItem.getInputStream();
        InputStreamReader ir = new InputStreamReader(is, "UTF-8");
        BufferedReader reader = new BufferedReader(ir);
        //读取文件
        ArrayList<UserTrack> userTracks = new ArrayList<>();
        String tmp;
        while ((tmp = reader.readLine()) != null) {
            String artist = tmp.split(",")[0];
            String trackname = tmp.split(",")[1];
            int duration = Integer.valueOf(tmp.split(",")[2]);
            UserTrack userTrack = new UserTrack();
            userTrack.setArtist(artist);
            userTrack.setTrackname(trackname);
            userTrack.setDuration(duration);
            int id = userTrackDao.getTrackId(userTrack);
            if (id <= 0)
                throw new MyException(Consts.TRACK_NOT_EXIST, "1");
            userTrack.setTrackID(id);
            User2 user2 = user2Dao.getByName(name);
            if (user2 == null)
                throw new MyException(Consts.USER_NOT_EXIST, "1");
            userTrack.setUserID(user2.getId());
            userTrack.setUsername(user2.getName());
            userTracks.add(userTrack);
        }
        //存入到数据库
        userTrackDao.save(userTracks);
    }

    /**
     * 获取喜欢歌曲轨道
     * @param name
     * @return
     */
    @Override
    @Transactional
    public ArrayList<Track> getFavor(String name) {
        User2 user2 = new User2();
        user2.setName(name);
        user2 = user2Dao.getUser2ByName(user2);
        if (user2 == null)
            throw new MyException(Consts.USER_NOT_EXIST, "1");
        ArrayList<UserTrack> userTracks = (ArrayList<UserTrack>) userTrackDao.selectUserTrack(user2.getId());
        ArrayList<Track> tracks = new ArrayList<>();
        for (UserTrack userTrack : userTracks) {
            Track track = trackDao.selectTrack(userTrack.getTrackID());
            if (track != null)
                tracks.add(track);
        }
        return tracks;
    }

}
