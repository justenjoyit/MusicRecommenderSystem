package com.yan.controller;

import com.yan.persist.data.JsonResult;
import com.yan.persist.entity.User;
import com.yan.persist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YZT on 2018/4/17.
 */
@Controller
public class RegController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult reg(@RequestBody User post) {
        JsonResult jsonResult = new JsonResult();
        try{
            //注册保存到数据库
            User user = userService.register(post);
            System.out.println(user);
            //生成邮件并发送
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
}
