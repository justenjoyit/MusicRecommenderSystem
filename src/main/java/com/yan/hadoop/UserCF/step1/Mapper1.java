package com.yan.hadoop.UserCF.step1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by YZT on 2018/5/7.
 */
public class Mapper1 extends Mapper<LongWritable, Text, Text, Text> {
    private Text outKey = new Text();
    private Text outValue = new Text();

    /**
     *
     * @param key 1 2
     * @param value A,1,1   C,3,5
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] values = value.toString().split(",");
        String userID = values[0];
        String itemID = values[1];
        String score = values[2];

        outKey.set(userID);
        outValue.set(itemID + "_" + score);

        context.write(outKey, outValue);
    }
}
