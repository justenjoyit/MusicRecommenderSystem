package com.yan.persist.dao;

import com.yan.persist.entity.Verify;
import org.springframework.stereotype.Repository;

/**
 * Created by YZT on 2018/5/23.
 */
@Repository
public interface VerifyDao {
    Verify findVerifyByMethod(String method);
    int save(Verify verify);
    int update(Verify verify);
}
