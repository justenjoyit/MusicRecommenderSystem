package com.yan.crawler;

import com.alibaba.fastjson.JSONArray;
import com.yan.crawler.persist.dao.TagDao;
import com.yan.crawler.persist.dao.TrackDao;
import com.yan.crawler.persist.dao.User2Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by YZT on 2018/5/1.
 */
@Repository
public class MyCrawler {

    private final String RECENT_TRACK = "http://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=";
    private final String TRACK_TAG = "http://ws.audioscrobbler.com/2.0/?method=track.gettoptags&artist=";
    private final String USER_FRIEND = "http://ws.audioscrobbler.com/2.0/?method=user.getfriends&user=";
    private final String API_KEY = "&api_key=b25b959554ed76058ac220b7b2e0a026";

    @Autowired
    private TrackDao trackDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private User2Dao user2Dao;

    //通过Get获取数据
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 查找username用户最近听歌的轨道
     *
     * @param username
     */
    public void getRecentTrack(String username) throws IOException, SAXException {
        String url = RECENT_TRACK + username + API_KEY;
        String result = sendGet(url);
        List<Track> trackList = ReadXMLByDom.getTracks(result);
        //保存到数据库
        trackDao.insert(trackList);
    }

    /**
     * 通过artist和track找到歌曲标签
     *
     * @param artist
     * @param track
     */
    public void getTrackTag(String artist, String track) throws IOException, SAXException {
        String url = TRACK_TAG + artist + "&track=" + track + API_KEY;
        String result = sendGet(url);
        List<Tag> tagList = ReadXMLByDom.getTags(result);
        //保存到数据库
        trackDao.updateTag(artist, track, JSONArray.toJSONString(tagList));
        tagDao.insert(tagList);
    }

    /**
     * 通过username找到他的朋友
     *
     * @param username
     * @throws IOException
     * @throws SAXException
     */
    public void getUserFriend(String username) throws IOException, SAXException {
        String url = USER_FRIEND + username + API_KEY;
        String result = sendGet(url);
        List<User2> user2List = ReadXMLByDom.getUsers(result);
        //保存到数据库
        user2Dao.insert(user2List);
    }
}
