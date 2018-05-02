package com.yan.crawler;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
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

    public static List<User> getUsers(String xml) throws IOException, SAXException {
        List<User> users = new ArrayList<User>();
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        document = db.parse(is);
        NodeList userList = document.getElementsByTagName("user");
        for (int i = 0; i < userList.getLength(); ++i) {
            User user = new User();
            Node node = userList.item(i);
            NodeList cList = node.getChildNodes();
            for (int j = 0; j < cList.getLength(); j += 2) {
                Node cNode = cList.item(j);
                if (cNode.getFirstChild() != null) {
                    String content = cNode.getFirstChild().getTextContent();
                    String nodeName = cNode.getNodeName();
                    switch (nodeName) {
                        case "name": {
                            user.setName(content);
                            break;
                        }
                        case "realname": {
                            user.setRealName(content);
                            break;
                        }
                        case "image": {
                            user.addImage(content);
                            break;
                        }
                        case "url": {
                            user.setUrl(content);
                            break;
                        }
                        case "country": {
                            user.setCountry(content);
                            break;
                        }
                        case "age": {
                            user.setAge(Integer.valueOf(content));
                            break;
                        }
                        case "gender": {
                            user.setGender(content);
                            break;
                        }
                        case "subscriber": {
                            user.setSubscriber(content);
                            break;
                        }
                        case "playcount": {
                            user.setPlaycount(Integer.valueOf(content));
                            break;
                        }
                        case "playlists": {
                            user.setPlaylists(Integer.valueOf(content));
                            break;
                        }
                        case "bootstrap": {
                            user.setBootstrap(Integer.valueOf(content));
                            break;
                        }
                        case "registered": {
                            user.setRegistered(content);
                            break;
                        }
                        case "alumni": {
                            user.setAlumni(content);
                            break;
                        }
                    }
                }

            }
            users.add(user);
        }
        return users;
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
            for (int j = 0; j < cList.getLength(); j += 2) {
                Node cNode = cList.item(j);
                if (cNode.getFirstChild() != null) {
                    String content = cNode.getFirstChild().getTextContent();
                    String nodeName = cNode.getNodeName();
                    switch (nodeName) {
                        case "artist": {
                            track.setArtist(content);
                            break;
                        }
                        case "name": {
                            track.setName(content);
                            break;
                        }
                        case "mbid": {
                            track.setMbid(content);
                            break;
                        }
                        case "url": {
                            track.setUrl(content);
                            break;
                        }
                        case "image": {
                            track.addImage(content);
                            break;
                        }
                    }
                }

            }
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
                        case "count": {
                            tag.setCount(Integer.valueOf(content));
                            break;
                        }
                        case "name": {
                            tag.setName(content);
                            break;
                        }
                        case "url": {
                            tag.setUrl(content);
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
