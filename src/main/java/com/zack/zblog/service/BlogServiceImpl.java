package com.zack.zblog.service;

import com.zack.zblog.dao.BlogDao;
import com.zack.zblog.dao.CategoryDao;
import com.zack.zblog.exception.HttpNotFoundException;
import com.zack.zblog.model.Blog;
import com.zack.zblog.model.BlogBase;
import com.zack.zblog.model.Category;
import com.zack.zblog.util.BlogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ZackJiang on 2018/5/27.
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<BlogBase> getBlogBases() {
        List<BlogBase> blogs = blogDao.getBlogBases();
        for (BlogBase blog : blogs) {
            String content = blog.getSummary();
            blog.setSummary(BlogUtil.getComment(content));
        }
        return blogs;
    }

    @Override
    public void addBlog(Blog blog) throws MethodArgumentNotValidException {
        isValidCategory(blog);
        blogDao.addBlog(blog);
    }

    @Override
    public void delBolog(int id) {
        if (!blogDao.isExist(id)) {
            throw new HttpNotFoundException();
        }
        blogDao.delBlog(id);
    }

    @Override
    public Blog getBlogById(int id) {
        Blog blog = blogDao.getBlogById(id);
        if (blog == null) {
            throw new HttpNotFoundException();
        }
        return blog;
    }

    @Override
    public void modifyBlog(Blog blog) throws MethodArgumentNotValidException {
        isValidCategory(blog);
        Blog oldBlog = blogDao.getBlogById(blog.getId());
        if (oldBlog == null) {
            throw new HttpNotFoundException();
        }
        blogDao.modifyBlog(blog);
    }

    private void isValidCategory(Blog blog) throws MethodArgumentNotValidException {
        List<Category> categories = categoryDao.getCategories();
        Set<Integer> ids = new HashSet<>();
        for (Category category : categories) {
            ids.add(category.getId());
        }
        if (!ids.contains(Integer.parseInt(blog.getCategory()))) {
            throw new MethodArgumentNotValidException(null, null);
        }
    }
}
