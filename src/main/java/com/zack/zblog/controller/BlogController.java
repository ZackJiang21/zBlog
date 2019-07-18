package com.zack.zblog.controller;

import com.zack.zblog.aop.Privilege;
import com.zack.zblog.model.Blog;
import com.zack.zblog.model.BlogBase;
import com.zack.zblog.service.BlogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ZackJiang on 2018/5/27.
 */
@RestController
@RequestMapping("/zblog/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<BlogBase> getBlogBases() {
        return blogService.getBlogBases();
    }

    @PostMapping
    @Privilege
    public void addBlog(@RequestBody Blog blog) throws MethodArgumentNotValidException {
        String title = blog.getTitle();
        if (StringUtils.isEmpty(blog.getCategory()) || isTitleInValid(title)) {
            throw new MethodArgumentNotValidException(null, null);
        }
        blogService.addBlog(blog);
    }

    @PutMapping(path = "/{id}")
    @Privilege
    public void modifyBlog(@RequestBody Blog blog, @PathVariable("id") int id) throws MethodArgumentNotValidException {
        String title = blog.getTitle();
        if (StringUtils.isEmpty(blog.getCategory()) || isTitleInValid(title)) {
            throw new MethodArgumentNotValidException(null, null);
        }
        blog.setId(id);
        blogService.modifyBlog(blog);
    }

    private boolean isTitleInValid(String title) {
        return StringUtils.isEmpty(title) || (title != null && title.length() > 128);
    }

    @DeleteMapping(path = "/{id}")
    @Privilege
    public void delBlog(@PathVariable("id") int id) {
        blogService.delBolog(id);
    }

    @GetMapping(path = "/{id}")
    public Blog getBlogById(@PathVariable("id") int id) {
        return blogService.getBlogById(id);
    }
}
