package com.yan.aop.service;

import com.yan.persist.data.Record;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by YZT on 2018/4/19.
 */
public interface RecordService {
    void addRecord(String className, long millis)throws IOException;
    ArrayList<String> readRecord(String fileName)throws IOException;
}
