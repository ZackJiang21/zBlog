package com.zack.zblog.service;

import com.zack.zblog.dao.UserDao;
import com.zack.zblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZackJiang on 2018/5/13.
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean auth(String name, String value) {
        User user = userDao.getUser(name, value);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
}
