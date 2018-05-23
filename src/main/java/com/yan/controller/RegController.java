package com.yan.controller;

import com.yan.persist.data.JsonResult;
import com.yan.persist.entity.User;
import com.yan.persist.service.UserService;
import com.yan.persist.service.VerifyService;
import com.yan.utils.MD5;
import com.yan.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YZT on 2018/4/17.
 */
@Controller
public class RegController {

    @Autowired
    private UserService userService;
    @Autowired
    private VerifyService verifyService;

    @RequestMapping(value = "/reg",method = RequestMethod.GET)
    public String index(){
        return "register";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult reg(@RequestBody User post) {
        System.out.println(post);
        JsonResult jsonResult = new JsonResult();
        try{
            //注册保存到数据库
            User user = userService.register(post);
            System.out.println(user);
            //生成邮件并发送
            sendMail(user);
        }catch (Exception e){
            jsonResult.setErrorCode("1");
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }

        //成功发送激活邮件
        jsonResult.setErrorCode("0");
        jsonResult.setMessage("激活邮件已发送");
        return jsonResult;
    }

    private void sendMail(User user) {
        //生成验证码并保存到数据库
        String code = verifyService.generateCode(user.getEmail(), user.getEmail(), 6);
        MailUtil.sendMail(user.getEmail(),code);
    }
}
