package com.zack.zblog.dao;

import com.zack.zblog.model.Blog;
import com.zack.zblog.model.BlogBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZackJiang on 2018/5/27.
 */
public interface BlogDao {
    List<BlogBase> getBlogBases();

    void addBlog(@Param("blog") Blog blog);

    boolean isExist(@Param("id") int id);

    void delBlog(@Param("id") int id);

    Blog getBlogById(@Param("id") int id);

    void modifyBlog(@Param("blog") Blog blog);
}
