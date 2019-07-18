package com.zack.zblog.service;

import com.zack.zblog.dao.CategoryDao;
import com.zack.zblog.exception.HttpNotFoundException;
import com.zack.zblog.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

/**
 * Created by ZackJiang on 2018/5/17.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Override
    public void delCategory(int id) throws MethodArgumentNotValidException {
        Category category = categoryDao.getCategoryById(id);
        if (category == null) {
            throw new HttpNotFoundException();
        }
        if (category.getBlogCount() > 0) {
            throw new MethodArgumentNotValidException(null, null);
        }
        categoryDao.delCategory(id);
    }
}
