package com.yan.hadoop.UserCF.step4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by YZT on 2018/5/7.
 */
public class Reducer4 extends Reducer<Text, Text, Text, Text> {
    private Text outKey = new Text();
    private Text outValue = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        for (Text value : values) {
            sb.append(value + ",");
        }
        String result = null;
        if (sb.toString().endsWith(","))
            result = sb.substring(0, sb.length() - 1);

        //outKey：行 outValue：列_值
        outKey.set(key);
        outValue.set(result);
        context.write(outKey,outValue);
    }
}
