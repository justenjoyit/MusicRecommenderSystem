package com.yan.hadoop.dao;

import com.yan.hadoop.UserCF.step2.MR2;
import com.yan.utils.Consts;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by YZT on 2018/5/9.
 */
@Repository("hdfsDa0")
public class HdfsDao {
    public void upload(String src, String dst) throws IOException, URISyntaxException {

        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://localhost:9000");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        FileSystem hdfs = FileSystem.get(conf);
        Path src_path = new Path(Consts.DATA_ROOT_PATH + src);
        Path dst_path = new Path(dst);
        hdfs.copyFromLocalFile(src_path, dst_path);
    }
}
