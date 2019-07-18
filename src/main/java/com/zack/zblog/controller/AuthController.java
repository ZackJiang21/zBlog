package com.zack.zblog.controller;

import com.zack.zblog.model.Response;
import com.zack.zblog.model.User;
import com.zack.zblog.service.AuthService;
import com.zack.zblog.util.TokenUtil;
import com.zack.zblog.util.ZBlogConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.TagUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by ZackJiang on 2018/5/13.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(method = RequestMethod.POST)
    public Response auth(HttpServletResponse response, @RequestBody User user) throws Exception {
        if (authService.auth(user.getName(), user.getValue())) {
            try {
                String token = TokenUtil.createJwt();
                Cookie cookie = new Cookie(ZBlogConst.TOKEN_NAME, URLEncoder.encode(token, "UTF-8"));
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                return new Response().success();
            } catch (UnsupportedEncodingException e) {
                logger.error("get token error");
                throw new Exception();
            }
        } else {
            return new Response().failure();

        }

    }
}
