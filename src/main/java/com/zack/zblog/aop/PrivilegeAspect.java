package com.zack.zblog.aop;

import com.zack.zblog.util.TokenUtil;
import com.zack.zblog.util.ZBlogConst;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ZackJiang on 2018/5/16.
 */
@Component
@Aspect
public class PrivilegeAspect {
    private static final Logger logger = LoggerFactory.getLogger(PrivilegeAspect.class);

    @Before("@annotation(com.zack.zblog.aop.Privilege)")
    public void auth() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ZBlogConst.TOKEN_NAME.equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }
        if (!TokenUtil.verifyJwt(token)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}