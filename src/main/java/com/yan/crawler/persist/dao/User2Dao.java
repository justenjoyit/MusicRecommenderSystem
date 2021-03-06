package com.yan.crawler.persist.dao;

import com.yan.crawler.data.User2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YZT on 2018/5/2.
 */
@Repository
public interface User2Dao {
    void insert(List<User2> users);

    List<User2> getUser2(int index);

    User2 getUser2ByName(User2 user2);

    User2 getByName(String name);

    void insertName(String email);
}
