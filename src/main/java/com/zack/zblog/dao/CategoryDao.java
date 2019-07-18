package com.zack.zblog.dao;

import com.zack.zblog.model.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZackJiang on 2018/5/16.
 */
public interface CategoryDao {
    void addCategory(@Param("category") Category category);

    List<Category> getCategories();

    Category getCategoryById(@Param("id") int id);

    void delCategory(@Param("id") int id);
}
