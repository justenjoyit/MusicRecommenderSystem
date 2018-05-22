package com.yan.controller.admin;

import com.alibaba.fastjson.JSON;
import com.yan.aop.service.RecordService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by YZT on 2018/4/19.
 */
@Controller
public class AdminController {

    @Autowired
    private RecordService recordService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
    public String login() {
        return "admin_login";
    }

    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@RequestBody User post) {
        JsonResult jsonResult = new JsonResult();

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(JSON.toJSONString(post), MD5.md5(post.getPassword()));
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

    /**
     * 查看网站性能
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/adminmain", method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws IOException {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated())
            return "admin_login";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> records = recordService.readRecord(simpleDateFormat.format(date) + ".json");
        request.setAttribute("performance", records);
        return "admin_main";
    }

    /**
     * 用户管理
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/usermanage", method = RequestMethod.GET)
    public String userManage(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated())
            return "admin_login";
        //获取所有用户
        ArrayList<User> users = userService.getAll();
        request.setAttribute("users", users);
        return "user_manage";
    }

    @RequestMapping(value = "/changeStatus", method = RequestMethod.GET)
    public String userManage(String email, HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated())
            return "admin_login";
        User user = userService.getUserByEmail(email);
        request.setAttribute("user", user);
        return "user_search";
    }
//    /**
//     * 查看网站性能
//     *
//     * @return
//     */
//    @RequestMapping(value = "/admin/viewSitePerformance", method = RequestMethod.GET)
//    public String viewPerformance(HttpServletRequest request) {
//        Subject currentUser = SecurityUtils.getSubject();
//        if (!currentUser.isAuthenticated())
//            return "admin_login";
////        JsonResult jsonResult = new JsonResult();
//        try {
//            //从文件中读取
//            Date date = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            ArrayList<String> records = recordService.readRecord(simpleDateFormat.format(date) + ".json");
//            request.setAttribute("performance", records);
////            jsonResult.setData(records);
////            jsonResult.setErrorCode("0");
////            return jsonResult;
//        } catch (Exception e) {
////            jsonResult.setErrorCode("1");
////            jsonResult.setMessage(e.getMessage());
////            return jsonResult;
//        }
//        return "admin_main";
//    }

    /**
     * 修改用户，只能修改status
     *
     * @param post
     * @return
     */
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult modifyUser(@RequestBody User post) {
        JsonResult jsonResult = new JsonResult();
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage("用户未登陆");
            return jsonResult;
        }

        try {
            userService.updateStatus(post);
            jsonResult.setErrorCode("0");
            jsonResult.setMessage("修改成功");
            return jsonResult;
        } catch (Exception e) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }
    }

    /**
     * 删除用户
     * @param email
     * @return
     */
    @RequestMapping(value = "/userdelete", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult deleteUser(String email) {
        JsonResult jsonResult = new JsonResult();
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage("用户未登陆");
            return jsonResult;
        }
        try {
            User post = new User();
            post.setEmail(email);
            userService.deleteUser(post);
            jsonResult.setErrorCode("0");
            jsonResult.setMessage("删除成功");
            return jsonResult;
        } catch (Exception e) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }
    }


}
