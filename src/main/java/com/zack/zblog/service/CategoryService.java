package com.zack.zblog.service;

import com.zack.zblog.model.Category;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

/**
 * Created by ZackJiang on 2018/5/16.
 */
public interface CategoryService {
    void addCategory(Category category);

    List<Category> getCategories();

    void delCategory(int id) throws MethodArgumentNotValidException;
}
