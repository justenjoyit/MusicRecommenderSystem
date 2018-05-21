package com.yan.controller.user;

import com.yan.persist.data.JsonResult;
import com.yan.persist.entity.User;
import com.yan.persist.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YZT on 2018/5/21.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            return "login";
        }
        System.out.println(currentUser.getPrincipal());
        User user = userService.getUserByEmail((String)currentUser.getPrincipal());
        request.setAttribute("user",user);
        return "user_info";
    }

    @RequestMapping(value = "/changepwd",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult changePwd(@RequestBody User post){
        JsonResult jsonResult = new JsonResult();
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            jsonResult.setErrorCode("1");
            jsonResult.setMessage("用户未登陆");
            return jsonResult;
        }

        try {
            userService.changePwd(post);
        }catch (Exception e){
            jsonResult.setErrorCode("1");
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }
        jsonResult.setErrorCode("0");
        jsonResult.setMessage("修改密码成功");
        return jsonResult;
    }
}
