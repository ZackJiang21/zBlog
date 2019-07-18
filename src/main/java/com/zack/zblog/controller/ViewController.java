package com.zack.zblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ZackJiang on 2018/7/17.
 */
@Controller("/")
public class ViewController {
    @GetMapping(path = "/")
    public String index() {
        return "redirect:/view/blog/blog.html";
    }

    @GetMapping(path = "/admin")
    public String admin() {
        return "redirect:/view/admin/console.html";
    }
}
