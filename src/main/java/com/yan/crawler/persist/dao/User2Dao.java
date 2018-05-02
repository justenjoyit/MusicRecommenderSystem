package com.yan.crawler.persist.dao;

import com.yan.crawler.User2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YZT on 2018/5/2.
 */
@Repository
public interface User2Dao {
    void insert(@Param("users")List<User2> users);
}
