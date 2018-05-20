package com.yan.realm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yan.persist.dao.UserDao;
import com.yan.persist.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by YZT on 2018/4/18.
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String email = (String) authenticationToken.getPrincipal();
        Object test = authenticationToken.getPrincipal();

        User currentUser = JSONObject.toJavaObject(JSON.parseObject((String)test),User.class);
        String password = String.valueOf(((UsernamePasswordToken) authenticationToken).getPassword());
        User user;
        user = userDao.getUser(currentUser.getEmail(), password, currentUser.getRole());


        if (user == null)
            throw new UnknownAccountException();
        if (user.getStatus() == 0)
            throw new AccountException();

        Date now = new Date();
        userDao.updateLastLogin(user.getId(), now);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getEmail(), authenticationToken.getCredentials(), getName());
        return authenticationInfo;
    }
}
