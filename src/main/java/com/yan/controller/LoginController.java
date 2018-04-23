package com.yan.controller;

import com.yan.persist.data.JsonResult;
import com.yan.persist.entity.User;
import com.yan.persist.service.UserService;
import com.yan.utils.MD5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by YZT on 2018/4/18.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@RequestBody User post) {
        System.out.println(post);
        JsonResult jsonResult = new JsonResult();

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(post.getEmail(), MD5.md5(post.getPassword()));
        try {
            currentUser.login(token);
        } catch (UnknownAccountException ue) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage("账户不存在或用户名或密码错误");
            return jsonResult;
        } catch (AccountException ae) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage("邮箱未激活");
            return jsonResult;
        }
        jsonResult.setErrorCode("0");
        jsonResult.setMessage("登录成功");
        return jsonResult;
    }
}
