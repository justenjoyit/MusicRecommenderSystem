package com.yan.recommend;

import com.alibaba.fastjson.JSONArray;
import com.yan.crawler.data.Tag;
import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;

import java.util.*;

/**
 * 基于标签的相似度计算
 * Created by YZT on 2018/5/6.
 */
public class MyRecommender {
    /**
     * 基于标签的歌曲相似度
     *
     * @param track1
     * @param track2
     * @return
     */
    public static double MusicSimilarity(Track track1, Track track2) {
        //将track1 和track2中的tags抽出来
        List<Tag> tag1 = JSONArray.parseArray(track1.getTags(), Tag.class);
        List<Tag> tag2 = JSONArray.parseArray(track2.getTags(), Tag.class);
        return caculate(tag1, tag2);
    }

    /**
     * 基于标签的用户相似度
     * @param user1
     * @param user2
     * @return
     */
    public static double userSimilariy(User2 user1, User2 user2) {
        //将user1 和user2中的tags抽出来
        List<Tag> tag1 = JSONArray.parseArray(user1.getTags(), Tag.class);
        List<Tag> tag2 = JSONArray.parseArray(user2.getTags(), Tag.class);
        return caculate(tag1, tag2);
    }

    /**
     * 计算相似度
     * @param tag1
     * @param tag2
     * @return
     */
    public static double caculate(List<Tag> tag1, List<Tag> tag2) {
        //将标签中的值放入数组
        Map<String, int[]> vectorMap = new HashMap<>();
        for (Tag tag : tag1) {
            if (vectorMap.containsKey(tag.getName())) {
                vectorMap.get(tag.getName())[0] = tag.getCount();
            } else {
                int[] tempArray = new int[2];
                tempArray[0] = tag.getCount();
                tempArray[1] = 0;
                vectorMap.put(tag.getName(), tempArray);
            }
        }
        for (Tag tag : tag2) {
            if (vectorMap.containsKey(tag.getName())) {
                vectorMap.get(tag.getName())[0] = tag.getCount();
            } else {
                int[] tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = tag.getCount();
                vectorMap.put(tag.getName(), tempArray);
            }
        }

        //获得公式分子
        double up = pointMulti(vectorMap);
        //获得公式分子
        double down = Math.sqrt(squares(vectorMap));
        //求得余弦相似度
        return up / down;
    }

    /**
     * 求平方和
     *
     * @param paramMap
     * @return
     */
    public static double squares(Map<String, int[]> paramMap) {
        double result1 = 0;
        double result2 = 0;
        Set<String> keySet = paramMap.keySet();
        for (String s : keySet) {
            int temp[] = paramMap.get(s);
            result1 += (temp[0] + temp[0]);
            result2 += (temp[1] + temp[1]);
        }
        return result1 * result2;
    }

    /**
     * 点乘法
     *
     * @param paramMap
     * @return
     */
    public static double pointMulti(Map<String, int[]> paramMap) {
        double result = 0;
        Set<String> keySet = paramMap.keySet();
        for (String s : keySet) {
            int[] temp = paramMap.get(s);
            result += (temp[0] * temp[1]);
        }
        return result;
    }
}
