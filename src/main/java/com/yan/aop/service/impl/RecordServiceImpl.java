package com.yan.aop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yan.persist.data.Record;
import com.yan.aop.service.RecordService;
import com.yan.utils.Files;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by YZT on 2018/4/19.
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Override
    public void addRecord(String className, long millis) throws IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ssss");

        Record record = new Record();
        record.setClassName(className);
        record.setMillis(millis);
        record.setTime(simpleDateFormat2.format(date));

        JSONObject object = (JSONObject) JSONObject.toJSON(record);
        Files.addData(simpleDateFormat.format(date) + ".json", object);

    }

    @Override
    public ArrayList<String> readRecord(String fileName) throws IOException {
        String data = Files.readData(fileName);
        String[] array = data.split("\n");
        ArrayList<String> datas = new ArrayList<>();
        Collections.addAll(datas, array);
        return datas;
    }
}
