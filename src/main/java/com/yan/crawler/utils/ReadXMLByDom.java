package com.yan.crawler.utils;

import com.alibaba.fastjson.JSONArray;
import com.yan.crawler.data.Tag;
import com.yan.crawler.data.Track;
import com.yan.crawler.data.User2;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YZT on 2018/5/1.
 */
public class ReadXMLByDom {
    private static DocumentBuilderFactory dbFactory = null;
    private static DocumentBuilder db = null;
    private static Document document = null;
    private final static String USER_FRIENDS = "http://ws.audioscrobbler.com/2.0/?method=user.getfriends&user=";
    private final static String API_KEY = "&api_key=b25b959554ed76058ac220b7b2e0a026";

    static {
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            db = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static List<User2> getUsers(String xml) throws IOException, SAXException {
        List<User2> user2s = new ArrayList<User2>();
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        document = db.parse(is);
        NodeList userList = document.getElementsByTagName("user");
        for (int i = 0; i < userList.getLength(); ++i) {
            User2 user2 = new User2();
            Node node = userList.item(i);
            NodeList cList = node.getChildNodes();
            ArrayList<String> images = new ArrayList<>();
            for (int j = 0; j < cList.getLength(); j += 2) {
                Node cNode = cList.item(j);
                if (cNode.getFirstChild() != null) {
                    String content = cNode.getFirstChild().getTextContent();
                    String nodeName = cNode.getNodeName();
                    switch (nodeName) {
                        case "name": {
                            user2.setName(content);
                            break;
                        }
                        case "realname": {
                            user2.setRealname(content);
                            break;
                        }
                        case "image": {
                            images.add(content);
                            break;
                        }
                        case "url": {
                            user2.setUrl(content);
                            break;
                        }
                        case "country": {
                            user2.setCountry(content);
                            break;
                        }
                        case "age": {
                            user2.setAge(Integer.valueOf(content));
                            break;
                        }
                        case "gender": {
                            user2.setGender(content);
                            break;
                        }
                        case "subscriber": {
                            user2.setSubscriber(content);
                            break;
                        }
                        case "playcount": {
                            user2.setPlaycount(Integer.valueOf(content));
                            break;
                        }
                        case "playlists": {
                            user2.setPlaylists(Integer.valueOf(content));
                            break;
                        }
                        case "bootstrap": {
                            user2.setBootstrap(Integer.valueOf(content));
                            break;
                        }
                        case "registered": {
                            user2.setRegistered(content);
                            break;
                        }
                        case "alumni": {
                            user2.setAlumni(content);
                            break;
                        }
                    }
                }

            }
            user2.setImages(JSONArray.toJSONString(images));
            user2s.add(user2);
        }
        return user2s;
    }

    public static List<Track> getTracks(String xml) throws IOException, SAXException {
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        document = db.parse(is);
        NodeList trackList = document.getElementsByTagName("track");
        List<Track> tracks = new ArrayList<Track>();
        for (int i = 0; i < trackList.getLength(); ++i) {
            Track track = new Track();
            Node node = trackList.item(i);
            NodeList cList = node.getChildNodes();
            ArrayList<String> images = new ArrayList<>();
            for (int j = 0; j < cList.getLength(); j += 2) {
                Node cNode = cList.item(j);
                if (cNode.getFirstChild() != null) {
                    String nodeName = cNode.getNodeName();
                    switch (nodeName) {
                        case "artist": {
                            track.setArtist(cNode.getFirstChild().getTextContent());
                            break;
                        }
                        case "name": {
                            track.setName(cNode.getTextContent());
                            break;
                        }
                        case "mbid": {
                            track.setMbid(cNode.getTextContent());
                            break;
                        }
                        case "url": {
                            track.setUrl(cNode.getTextContent());
                            break;
                        }
                        case "image": {
                            images.add(cNode.getTextContent());
                            break;
                        }
                        case "duration":{
                            track.setDuration(Integer.valueOf(cNode.getTextContent()));
                            break;
                        }
                    }
                }

            }
            track.setImages(JSONArray.toJSONString(images));
            tracks.add(track);
        }
        return tracks;
    }

    public static List<Tag> getTags(String xml) throws IOException, SAXException {
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        document = db.parse(is);
        NodeList trackList = document.getElementsByTagName("tag");
        List<Tag> tags = new ArrayList<Tag>();
        for (int i = 0; i < trackList.getLength(); ++i) {
            Tag tag = new Tag();
            Node node = trackList.item(i);
            NodeList cList = node.getChildNodes();
            for (int j = 0; j < cList.getLength(); j += 2) {
                Node cNode = cList.item(j);
                if (cNode.getFirstChild() != null) {
                    String content = cNode.getFirstChild().getTextContent();
                    String nodeName = cNode.getNodeName();
                    switch (nodeName) {
                        case "name": {
                            tag.setName(content);
                            break;
                        }
                        case "count":{
                            tag.setCount(Integer.valueOf(content));
                            break;
                        }
                    }
                }

            }
            tags.add(tag);
        }
        return tags;
    }
}
