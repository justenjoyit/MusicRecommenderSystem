package com.yan.persist.service.impl;

import com.yan.persist.dao.UserDao;
import com.yan.persist.entity.User;
import com.yan.persist.service.UserService;
import com.yan.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YZT on 2018/4/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User register(User post) {
        //查找数据库是否有同username或email的用户
        User user = userDao.findUserByUsernameOrEmail(post);
        if(user != null){
            throw new IllegalArgumentException("用户名或邮箱重复");
        }
        user = post;
        user.setStatus(0);
        user.setPassword(MD5.md5(user.getPassword()));
        userDao.save(user);

        return user;
    }
}
