package com.tensquare.user.service;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 1.id
     * 2.用户昵称
     * 3.用户头像
     */
    public void save(User user) {
        user.setId(UUID.randomUUID().toString());
        userDao.save(user);
    }
}
