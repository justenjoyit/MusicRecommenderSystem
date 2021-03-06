package com.yan.hadoop.ItemCF.step4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by YZT on 2018/5/7.
 */
public class MR4 {
    //输入文件相对路径
    private static String inPath = "/ItemCF/step2_output";
    //输出文件的相对路径
    private static String outPath = "/ItemCF/step4_output";
    //将step1输出的转置矩阵作为全局缓存
    private static String cache = "/ItemCF/step3_output/part-r-00000";
    //hdfs地址
    private static String hdfs = "hdfs://localhost:9000";
    //创建Job配置类
    public static Configuration conf = new Configuration();

    public int run(){
        try {

            //设置hdfs的地址
            conf.set("fs.defaultFS", hdfs);
            //创建一个job实例
            Job job = new Job(conf, "step4");
            //添加分布式缓存文件
//            job.addCacheFile(new URI(cache+"#itemUserScore2"));
            //设置job的主类
            job.setJarByClass(MR4.class);
            //设置job的Mapper类和Reducer类
            job.setMapperClass(Mapper4.class);
            job.setReducerClass(Reducer4.class);

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
//        catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
        return -1;
    }
}
