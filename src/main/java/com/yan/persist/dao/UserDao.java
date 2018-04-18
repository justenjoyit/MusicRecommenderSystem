package com.yan.persist.dao;

import com.yan.persist.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by YZT on 2018/4/18.
 */
@Repository
public interface UserDao {
    User findUserByUsernameOrEmail(User user);
    void save(User user);
}
