package com.yan.controller.admin;

import com.yan.aop.service.RecordService;
import com.yan.persist.data.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 查看网站性能
     * @return
     */
    @RequestMapping(value = "/admin/viewSitePerformance")
    @ResponseBody
    public JsonResult viewPerformance() {
        JsonResult jsonResult = new JsonResult();
        try{
            //从文件中读取
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ArrayList<String> records = recordService.readRecord(simpleDateFormat.format(date)+".json");
            jsonResult.setData(records);
            jsonResult.setErrorCode("0");
            return jsonResult;
        }catch (Exception e){
            jsonResult.setErrorCode("1");
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }

    }
}
