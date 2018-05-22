package com.yan.controller.user;

import com.yan.crawler.data.Track;
import com.yan.persist.data.JsonResult;
import com.yan.persist.entity.User;
import com.yan.persist.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by YZT on 2018/5/21.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 展示用户信息界面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            return "login";
        }
        System.out.println(currentUser.getPrincipal());
        User user = userService.getUserByEmail((String) currentUser.getPrincipal());
        request.setAttribute("user", user);
        return "user_info";
    }

    /**
     * 修改密码
     *
     * @param post
     * @return
     */
    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult changePwd(@RequestBody User post) {
        JsonResult jsonResult = new JsonResult();
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage("用户未登陆");
            return jsonResult;
        }

        try {
            userService.changePwd(post);
        } catch (Exception e) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }
        jsonResult.setErrorCode("0");
        jsonResult.setMessage("修改密码成功");
        return jsonResult;
    }

    /**
     * 展示上传文件界面
     *
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            return "login";
        }
        return "upload";
    }

    /**
     * 上传文件
     *
     * @param multipartFiles
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JsonResult upload(@RequestParam("file") MultipartFile multipartFiles) {
        JsonResult jsonResult = new JsonResult();
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage("用户未登陆");
            return jsonResult;
        }
        try {
            userService.upload((String) currentUser.getPrincipal(), multipartFiles);
        } catch (Exception e) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }

        jsonResult.setErrorCode("0");
        jsonResult.setMessage("上传成功");
        return jsonResult;
    }

    @RequestMapping(value = "/userfavorite", method = RequestMethod.GET)
    public String favor() {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            return "login";
        }
        return "user_favorite";
    }

    @RequestMapping(value = "/favor", method = RequestMethod.GET)
    public String favor(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            return "login";
        }
        ArrayList<Track> tracks = userService.getFavor((String) currentUser.getPrincipal());
        request.setAttribute("tracks", tracks);
        return "user_favorite";
    }
}
