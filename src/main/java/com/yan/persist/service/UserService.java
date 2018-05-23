package com.yan.persist.service;

import com.yan.crawler.data.Track;
import com.yan.persist.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by YZT on 2018/4/18.
 */
public interface UserService {

    User register(User post);

    void updateStatus(User post);

    void deleteUser(User post);

    User getUserByName(String name);

    void changePwd(User user);

    ArrayList<User> getAll();

    void upload(String email, MultipartFile multipartFile) throws IOException;

    ArrayList<Track> getFavor(String name);

    void updateStatusByEmail(String email, int status);
}
