package com.yan.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 * Created by YZT on 2018/5/2.
 */
public class Files {
    /**
     * 向文件添加数据
     * @param filename
     * @param jsonObject
     * @throws IOException
     */
    public static void addData(String filename, JSONObject jsonObject) throws IOException {
        String path = Consts.DATA_ROOT_PATH + filename;
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        // BufferedWriter writer give better performance
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(jsonObject.toJSONString());
        bw.write("\n");
        bw.close();
        fw.close();
    }

    /**
     * 从文件中读去数据
     * @param filename
     * @return
     * @throws IOException
     */
    public static String readData(String filename) throws IOException {
        String result;
        String path = Consts.DATA_ROOT_PATH + filename;
        File file = new File(path);
        FileInputStream fip = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
        StringBuffer sb = new StringBuffer();
        while (reader.ready()) {
            sb.append((char) reader.read());
        }
        result = sb.toString();
        reader.close();
        fip.close();
        return result;
    }

    /**
     * 删除文件
     * @param path
     * @return
     */
    public static boolean deleteDir(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            for (File f : dir.listFiles()) {
                if (f.isDirectory()) {
                    deleteDir(f.getName());
                } else {
                    f.delete();
                }
            }
            dir.delete();
            return true;
        } else {
            System.out.println("文件(夹)不存在!");
            return false;
        }
    }
}
