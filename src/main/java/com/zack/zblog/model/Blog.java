package com.zack.zblog.model;

/**
 * Created by ZackJiang on 2018/5/27.
 */
public class Blog extends BlogBase {
    String content;

    String viewContent;

    String img;

    public Blog() {
        super();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getViewContent() {
        return viewContent;
    }

    public void setViewContent(String viewContent) {
        this.viewContent = viewContent;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
