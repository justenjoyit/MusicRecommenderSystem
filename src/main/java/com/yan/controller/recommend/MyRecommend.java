package com.yan.controller.recommend;

import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;
import com.yan.hadoop.ItemCF.ItemCFJobRunner;
import com.yan.hadoop.UserCF.UserCFJobRunner;
import com.yan.persist.data.JsonResult;
import com.yan.persist.service.ItemCF;
import com.yan.persist.service.Score;
import com.yan.persist.service.UserCF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by YZT on 2018/5/9.
 */
@Controller
public class MyRecommend {
    //判断是否需要重新计算
    protected static volatile boolean flag = true;

    @Autowired
    private UserCF userCF;
    @Autowired
    private ItemCF itemCF;
    @Autowired
    private Score score;

    @RequestMapping(value = "/showUserCF", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult userCF(@RequestBody User2 user2) {
        JsonResult jsonResult = new JsonResult();
        List<Track> tracks;
        try {
            //首先判断是否需要重新计算基于用户的推荐列表
            if (flag) {
                score.caculate();
                UserCFJobRunner.run();
            }
            //返回该用户的推荐列表
            tracks = userCF.getUserCF(user2);
        } catch (Exception e) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }
        jsonResult.setErrorCode("0");
        jsonResult.setData(tracks);
        return jsonResult;
    }

    @RequestMapping(value = "/showItemCF", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult itemCF(@RequestBody User2 user2) {
        JsonResult jsonResult = new JsonResult();
        List<Track> tracks;
        try {
            //首先判断是否需要重新计算基于用户的推荐列表
            if (flag) {
                score.caculate();
                ItemCFJobRunner.run();
            }
            //返回该用户的推荐列表
            tracks = itemCF.getUserCF(user2);
        } catch (Exception e) {
            jsonResult.setErrorCode("1");
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }
        jsonResult.setErrorCode("0");
        jsonResult.setData(tracks);
        return jsonResult;
    }


}
