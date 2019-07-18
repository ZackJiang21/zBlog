package com.zack.zblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ZackJiang on 2018/5/8.
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "com.zack.zblog.dao")
@ComponentScan(basePackages = "com.zack.zblog")
public class Zblog {
    public static void main(String[] args) {
        SpringApplication.run(Zblog.class, args);
    }
}
