package com.zack.zblog.model;

/**
 * Created by ZackJiang on 2018/5/16.
 */
public class Category {
    private int id;

    private String name;

    private int blogCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(int blogCount) {
        this.blogCount = blogCount;
    }
}
