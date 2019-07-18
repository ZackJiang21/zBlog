package com.zack.zblog.dao;

import com.zack.zblog.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ZackJiang on 2018/5/13.
 */
public interface UserDao {
    User getUser(@Param("name") String name, @Param("value") String value);
}
