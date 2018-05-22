package com.yan.persist.service.impl;

import com.yan.exception.MyException;
import com.yan.persist.dao.UserDao;
import com.yan.persist.entity.User;
import com.yan.persist.service.UserService;
import com.yan.utils.Consts;
import com.yan.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by YZT on 2018/4/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

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

}
