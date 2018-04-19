package com.yan.persist.service;

import com.yan.persist.entity.User;

/**
 * Created by YZT on 2018/4/18.
 */
public interface UserService {

    User register(User post);

    void updateStatus(User post);

    void deleteUser(User post);
}
