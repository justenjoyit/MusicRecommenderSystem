package com.yan.hadoop.UserCF.step3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by YZT on 2018/5/7.
 */
public class MR3 {

    //输入文件相对路径
    private static String inPath = "/UserCF/step1_output";
    //输出文件的相对路径
    private static String outPath = "/UserCF/step3_output";
    //hdfs地址
    private static String hdfs = "hdfs://localhost:9000";

    public int run() {
        try {
            //创建Job配置类
            Configuration conf = new Configuration();
            //设置hdfs的地址
            conf.set("fs.defaultFS", hdfs);
            //创建一个job实例
            Job job = Job.getInstance(conf, "step3");

            //设置job的主类
            job.setJarByClass(MR3.class);
            //设置job的Mapper类和Reducer类
            job.setMapperClass(Mapper3.class);
            job.setReducerClass(Reducer3.class);

            //设置Mapper输出的类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            //设置Reducer输出的类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            //设置输入和输出路径
            FileSystem fs = FileSystem.get(conf);
            Path inputPath = new Path(inPath);
            System.out.println(fs.exists(inputPath));
            if (fs.exists(inputPath)) {
                FileInputFormat.addInputPath(job, inputPath);
            }
            Path outputPath = new Path(outPath);
            fs.delete(outputPath, true);

            FileOutputFormat.setOutputPath(job, outputPath);

            return job.waitForCompletion(true) ? 1 : -1;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
