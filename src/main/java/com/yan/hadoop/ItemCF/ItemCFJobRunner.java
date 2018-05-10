package com.yan.hadoop.ItemCF;

import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;
import com.yan.hadoop.ItemCF.step1.MR1;
import com.yan.hadoop.ItemCF.step2.MR2;
import com.yan.hadoop.ItemCF.step3.MR3;
import com.yan.hadoop.ItemCF.step4.MR4;
import com.yan.hadoop.ItemCF.step5.MR5;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZT on 2018/5/8.
 */
public class ItemCFJobRunner {
    public static void run(){
        int status1 = -1;
        int status2 = -1;
        int status3 = -1;
        int status4 = -1;
        int status5 = -1;

        status1 = new MR1().run();
        if(status1 == 1){
            System.out.println("Step1运行成功，开始运行Step2...");
            status2 = new MR2().run();
        }else {
            System.out.println("Step1运行失败");
        }
        if(status2 == 1){
            System.out.println("Step2运行成功，开始运行Step3...");
            status3 = new MR3().run();
        }else {
            System.out.println("Step2运行失败");
        }
        if(status3 == 1){
            System.out.println("Step3运行成功，开始运行Step4...");
            status4 = new MR4().run();
        }else {
            System.out.println("Step3运行失败");
        }
        if(status4 == 1){
            System.out.println("Step4运行成功，开始运行Step5...");
            status5 = new MR5().run();
        }else {
            System.out.println("Step4运行失败");
        }
        if(status5 == 1){
            System.out.println("Step5运行成功...");
        }else {
            System.out.println("Step5运行失败");
        }
    }

    public static List<Track> getRecommend(User2 user2) throws IOException {
        List<String> cacheList = new ArrayList<>();
        //将计算结果读入List<String>中
        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://localhost:9000");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        FileSystem fileSystem = FileSystem.get(conf);
        Path matrix2 = new Path("/ItemCF/step5_output/part-r-00000");
        FSDataInputStream in = null;
        in = fileSystem.open(matrix2);
        InputStreamReader isr = new InputStreamReader(in, "utf-8");
        BufferedReader br = new BufferedReader(isr);

        //每一行的格式是：用户ID tab 物品ID_值,物品ID_值
        String line = null;
        while ((line = br.readLine()) != null) {
            cacheList.add(line);
        }

        br.close();
        isr.close();
        in.close();
        //遍历list找到user2对应的那一行
        List<Track> tracks = new ArrayList<>();

        //cache格式是：用户ID tab 物品ID_值,物品ID_值
        for (String cache : cacheList) {
            int userID = Integer.valueOf(cache.toString().split("\t")[0]);
            if (userID == user2.getId()) {
                String[] trackStr = cache.toString().split("\t")[1].split(",");
                //track_value格式：物品ID_值
                for (String track_value : trackStr) {
                    int itemID = Integer.valueOf(track_value.split("_")[0]);
                    double recommend = Double.valueOf(track_value.split("_")[1]);
                    Track track = new Track();
                    track.setId(itemID);
                    track.setRecommend(recommend);
                    tracks.add(track);
                }
            }
        }
        return tracks;
    }
}
