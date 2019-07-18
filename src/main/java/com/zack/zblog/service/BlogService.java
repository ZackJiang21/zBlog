package com.zack.zblog.service;

import com.zack.zblog.model.Blog;
import com.zack.zblog.model.BlogBase;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

/**
 * Created by ZackJiang on 2018/5/27.
 */
public interface BlogService {
    List<BlogBase> getBlogBases();

    void addBlog(Blog blog) throws MethodArgumentNotValidException;

    void delBolog(int id);

    Blog getBlogById(int id);

    void modifyBlog(Blog blog) throws MethodArgumentNotValidException;
}
