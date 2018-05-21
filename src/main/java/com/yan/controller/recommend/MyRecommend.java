package com.yan.controller.recommend;

import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;
import com.yan.hadoop.ItemCF.ItemCFJobRunner;
import com.yan.hadoop.UserCF.UserCFJobRunner;
import com.yan.persist.data.JsonResult;
import com.yan.persist.service.ItemCF;
import com.yan.persist.service.Score;
import com.yan.persist.service.UserCF;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZT on 2018/5/9.
 */
@Controller
public class MyRecommend {
    //判断是否需要重新计算
    protected static volatile boolean caculate_flag = true;
    protected static volatile boolean userCF_flag = true;
    protected static volatile boolean itemCF_flag = true;

    @Autowired
    private UserCF userCF;
    @Autowired
    private ItemCF itemCF;
    @Autowired
    private Score score;

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String recommend() {
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated())
            return "login";
        return "main_recommend";
    }

    @RequestMapping(value = "/showUserCF", method = RequestMethod.GET)
//    @ResponseBody
    public String userCF(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            return "login";
        }
//        JsonResult jsonResult = new JsonResult();
        List<Track> tracks;
        try {
            //首先判断是否需要重新计算基于用户的推荐列表
//            if (caculate_flag) {
//                score.caculate();
//                caculate_flag = false;
//            }
//            if (userCF_flag)
//                UserCFJobRunner.run();

            User2 user2 = new User2();
            user2.setName((String)currentUser.getPrincipal());
            //返回该用户的推荐列表
            tracks = userCF.getUserCF(user2);
            request.setAttribute("recommend", tracks);
        } catch (Exception e) {
//            jsonResult.setErrorCode("1");
//            jsonResult.setMessage(e.getMessage());
//            return jsonResult;
        }
//        jsonResult.setErrorCode("0");
//        jsonResult.setData(tracks);
//        return jsonResult;
        return "recommend";
    }

    @RequestMapping(value = "/showItemCF", method = RequestMethod.GET)
//    @ResponseBody
    public String itemCF(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            return "login";
        }
//        JsonResult jsonResult = new JsonResult();
        List<Track> tracks;
        try {
            //首先判断是否需要重新计算基于用户的推荐列表
//            if (caculate_flag) {
//                score.caculate();
//                caculate_flag = false;
//            }
//            if (itemCF_flag)
//                ItemCFJobRunner.run();

            User2 user2 = new User2();
            user2.setName((String)currentUser.getPrincipal());
            //返回该用户的推荐列表
            tracks = itemCF.getItemCF(user2);
            request.setAttribute("recommend", tracks);

        } catch (Exception e) {
//            jsonResult.setErrorCode("1");
//            jsonResult.setMessage(e.getMessage());
//            return jsonResult;
        }
//        jsonResult.setErrorCode("0");
//        jsonResult.setData(tracks);
//        return jsonResult;
        return "recommend";
    }


    @RequestMapping(value = "/recommend1", method = RequestMethod.GET)
    public String recommend(HttpServletRequest request, HttpServletResponse response) {
        List<Track> tracks = new ArrayList<>();
        Track track1 = new Track();
        track1.setArtist("artist1");
        track1.setName("name1");
        track1.setUrl("www.url1.com");
        Track track2 = new Track();
        track2.setArtist("artist2");
        track2.setName("name2");
        track2.setUrl("www.url2.com");
        tracks.add(track1);
        tracks.add(track2);
        request.setAttribute("recommend", tracks);
        return "recommend";
    }
}
