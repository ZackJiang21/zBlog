package com.zack.zblog.controller;

import com.zack.zblog.aop.Privilege;
import com.zack.zblog.model.Category;
import com.zack.zblog.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ZackJiang on 2018/5/16.
 */
@RestController
@RequestMapping("/zblog/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @Privilege
    public void addCategory(@RequestBody Category category) throws MethodArgumentNotValidException {
        String name = category.getName();
        if (StringUtils.isEmpty(name) || (name != null && name.length() > 64)) {
            throw new MethodArgumentNotValidException(null, null);
        }
        categoryService.addCategory(category);
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @DeleteMapping(path = "/{id}")
    @Privilege
    public void delCategory(@PathVariable("id") int id) throws MethodArgumentNotValidException {
        categoryService.delCategory(id);
    }
}
