package com.yan.persist.service.impl;

import com.yan.crawler.data.User2;
import com.yan.crawler.data.UserTrack;
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

    @Override
    public void updateStatus(User post) {
        userDao.updateStatus(post);
    }

    @Override
    public void deleteUser(User post) {
        userDao.deleteUser(post);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return userDao.findUserByUsernameOrEmail(user);
    }

    @Override
    public void changePwd(User post) {
        User user = userDao.findUserByUsernameOrEmail(post);
        if (user == null)
            throw new MyException(Consts.USER_NOT_EXIST, "0");
        post.setPassword(MD5.md5(post.getPassword()));
        userDao.updatePwd(post);
    }

    @Override
    public ArrayList<User> getAll() {
        return userDao.getAll();
    }

    @Override
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

}
