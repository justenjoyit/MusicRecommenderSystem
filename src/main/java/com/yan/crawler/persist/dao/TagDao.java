package com.yan.crawler.persist.dao;

import com.yan.crawler.data.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZT on 2018/5/2.
 */
@Repository
public interface TagDao {
    void insert(@Param("tags")List<Tag> tags);
    ArrayList<Tag> selectAll();
}
