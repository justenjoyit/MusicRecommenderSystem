package com.yan.crawler;

import com.alibaba.fastjson.JSONArray;
import com.yan.crawler.data.Tag;
import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;
import com.yan.crawler.persist.dao.TagDao;
import com.yan.crawler.persist.dao.TrackDao;
import com.yan.crawler.persist.dao.User2Dao;
import com.yan.crawler.utils.ReadXMLByDom;
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
    private final String USER_TOP_TRACK = "http://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user=";
    private final String USER_LOVED_TRACK = "http://ws.audioscrobbler.com/2.0/?method=user.getlovedtracks&user=";
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
//    public void getRecentTrack(String username) throws IOException, SAXException {
//        String newUsername = username.replaceAll(" ", "+");
//        String url = RECENT_TRACK + newUsername + API_KEY;
//        String result = sendGet(url);
//        List<Track> trackList = ReadXMLByDom.getTracks(result);
//        //保存到track
//        if (trackList.size() != 0) {
//            trackDao.insert(trackList);
//            //将用户和其最近收听轨道的关系保存到user_track
//            trackDao.insertUserTrack(username, trackList);
//        }
//    }

    /**
     * 通过artist和track找到歌曲标签
     *
     * @param artist
     * @param track
     */
    public void getTrackTag(String artist, String track) throws IOException, SAXException {
        String newArtist = artist.replaceAll(" ", "+");
        String newTrack = track.replaceAll(" ", "+");
        String url = TRACK_TAG + newArtist + "&track=" + newTrack + API_KEY;
        String result = sendGet(url);
        List<Tag> tagList = ReadXMLByDom.getTags(result);
        //更新track表，将新标签保存到tag表
        trackDao.updateTag(artist, track, JSONArray.toJSONString(tagList));
        if (tagList.size() != 0)
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
        String newUsername = username.replaceAll(" ", "+");
        String url = USER_FRIEND + newUsername + API_KEY;
        String result = sendGet(url);
        List<User2> user2List = ReadXMLByDom.getUsers(result);
        //保存到user2
        if (user2List != null && user2List.size() != 0)
        user2Dao.insert(user2List);
    }

    public void getTopTrack(User2 user2) throws IOException, SAXException {
        String newUsername = user2.getName().replaceAll(" ", "+");
        String url = USER_TOP_TRACK + newUsername + API_KEY;
        String result = sendGet(url);
        List<Track> user_track = ReadXMLByDom.getTracks(result);
        if (user_track != null && user_track.size() > 0) {
            System.out.println("-------------------------------");
            System.out.println(user_track);
            System.out.println("-------------------------------");
            trackDao.insert(user_track);
            trackDao.insertUserTrack(user2, user_track);
        }
    }

    /**
     * 正式爬取
     * 一个线程getUserInfoThread用来获取用户及其最近听取的列表
     * 另一个线程getTrackInfoThread用来获取数据库中轨道的标签
     */
    public void crawl() throws InterruptedException {
        final Thread getUserInfoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int userIndex = 0;
                while (userIndex <= 1000) {
                    //从数据库中每次取出20条数据
                    List<User2> user2List = user2Dao.getUser2(userIndex);
                    userIndex += user2List.size();
                    //对每条数据均查询后添加到user2中
                    //对每条数据均查询最近收听列表然后添加到track中
                    for (User2 user2 : user2List) {
                        try {
                            getUserFriend(user2.getName());
                            getTopTrack(user2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

//        Thread getTrackInfoThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int trackIndex = 0;
//                while (getUserInfoThread.isAlive() || (trackDao.count() > (trackIndex + 1))) {
//                    List<Track> trackList = trackDao.getTrack(trackIndex);
//                    trackIndex += trackList.size();
//                    //对每条轨道均查询其标签并更新track表，将新标签保存到tag表
//                    for (Track track : trackList) {
//                        try {
//                            getTrackTag(track.getArtist(), track.getName());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (SAXException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
        getUserInfoThread.start();
        getUserInfoThread.join();
//        getTrackInfoThread.start();
//        getTrackInfoThread.join();
    }
}
