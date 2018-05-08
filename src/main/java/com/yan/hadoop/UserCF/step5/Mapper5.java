package com.yan.hadoop.UserCF.step5;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZT on 2018/5/8.
 */
public class Mapper5 extends Mapper<LongWritable, Text, Text, Text> {
    private Text outKey = new Text();
    private Text outValue = new Text();

    private List<String> cacheList = new ArrayList<>();

    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        //通过输入流将全局缓存中的右侧矩阵读入List<String>中
        FileSystem fileSystem = FileSystem.get(MR5.conf);
        Path matrix2 = new Path("/UserCF/step1_output/part-r-00000");
        FSDataInputStream in = null;
        in = fileSystem.open(matrix2);
        InputStreamReader isr = new InputStreamReader(in, "utf-8");
        BufferedReader br = new BufferedReader(isr);

        //每一行的格式是：行 tab 列_值,列_值
        String line = null;
        while ((line = br.readLine()) != null) {
            cacheList.add(line);
        }

        br.close();
        isr.close();
        in.close();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String item_matrix1 = value.toString().split("\t")[0];
        String[] user_score_array_matrix1 = value.toString().split("\t")[1].split(",");

        for (String line : cacheList) {
            String item_matrix2 = line.toString().split("\t")[0];
            String[] user_score_array_matrix2 = line.toString().split("\t")[1].split(",");

            //如果物品ID相同
            if (item_matrix1.equals(item_matrix2)) {
                //遍历matrix1的列
                for (String user_score_matrix1 : user_score_array_matrix1) {
                    boolean flag = false;
                    String user_matrix1 = user_score_matrix1.split("_")[0];
                    String score_matrix1 = user_score_matrix1.split("_")[1];

                    //遍历matrix2的列
                    for (String user_score_matrix2 : user_score_array_matrix2) {
                        String user_matrix2 = user_score_matrix2.split("_")[0];
                        if (user_matrix1.equals(user_matrix2)) {
                            flag = true;
                        }
                    }
                    if (flag == false) {
                        outKey.set(item_matrix1);
                        outValue.set(user_matrix1 + "_" + score_matrix1);
                        context.write(outKey, outValue);
                    }
                }
            }
        }

    }
}
