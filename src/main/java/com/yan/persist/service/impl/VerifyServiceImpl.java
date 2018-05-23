package com.yan.persist.service.impl;

import com.yan.exception.MyException;
import com.yan.persist.dao.VerifyDao;
import com.yan.persist.entity.Verify;
import com.yan.persist.service.UserService;
import com.yan.persist.service.VerifyService;
import com.yan.utils.MD5;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by YZT on 2018/5/23.
 */
@Service
public class VerifyServiceImpl implements VerifyService {
    @Autowired
    private VerifyDao verifyDao;
    @Autowired
    private UserService userService;
    //验证码存活时间 单位：分钟
    private int survivalTime = 30;

    /**
     * 生成验证码
     * 先查看是否有此用户的验证码信息
     * 有的话是否过期
     * 没有或没有过期就重新生成
     *
     * @param target
     * @param verify_method
     * @return
     */
    @Override
    @Transactional
    public String generateCode(String target, String verify_method, int codeLength) {

        //随机生成验证码、生成时间
        String code = RandomStringUtils.randomNumeric(codeLength);
        Date now = new Date();

        //查看数据库是否有此verify_method的验证码
        Verify verify = verifyDao.findVerifyByMethod(MD5.md5(verify_method));
        if (verify == null) {
            verify = new Verify();
            verify.setCode(code);
            verify.setCreated(now);
            verify.setExpired(DateUtils.addMinutes(now, survivalTime));
            verify.setStatus(0);
            verify.setTarget(target);
            verify.setVerify_method(MD5.md5(verify_method));
            verifyDao.save(verify);
            return code;
        }

        //数据库中有此用户的验证码
        long interval = (now.getTime() - verify.getCreated().getTime()) / 1000;

        if (interval <= 60) {
            throw new MyException("发送间隔不得小于1分钟", "1");
        }

        //更新验证码到数据库
        verify.setCode(code);
        verify.setCreated(now);
        verify.setExpired(DateUtils.addMinutes(now, survivalTime));
        verify.setStatus(0);
        verifyDao.update(verify);

        return code;
    }

    /**
     * 验证码验证
     * 通过verify_method进行查库
     * 如果验证码没有超时并且code正确并且没有重复验证则正确返回
     * 否则抛相应异常
     *
     * @param verify_method
     * @param code
     */
    @Override
    @Transactional
    public void verify(String verify_method, String code) {
        Verify verify = verifyDao.findVerifyByMethod(MD5.md5(verify_method));

        if (verify == null) {
            throw new MyException("验证码不存在", "1");
        }

        Date now = new Date();
        long interval = (now.getTime() - verify.getCreated().getTime()) / 1000 / 60;
        if (interval > survivalTime) {
            throw new MyException("验证码超时", "1");
        }

        if (!code.equals(verify.getCode())) {
            throw new MyException("验证码错误", "1");
        }

        if (verify.getStatus() != 0) {
            throw new MyException("验证码已验证", "1");
        }
        //验证成功，更新验证码
        verify.setStatus(1);
        verifyDao.update(verify);
    }

}
