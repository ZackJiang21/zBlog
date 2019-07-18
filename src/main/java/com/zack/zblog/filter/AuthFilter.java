package com.zack.zblog.filter;

import com.google.common.collect.Sets;
import com.zack.zblog.util.TokenUtil;
import com.zack.zblog.util.ZBlogConst;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.HttpSessionRequiredException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by ZackJiang on 2018/5/15.
 */
@WebFilter(filterName = "authFilter", urlPatterns = {"/view/admin/*", "/js/admin/*", "/css/admin.css"},
        initParams = {
                @WebInitParam(name = "exclusions", value = "/view/admin/index.html,/js/admin/index.js")
        })
public class AuthFilter implements Filter {
    private static Set<String> excludeURI = Sets.newHashSet();

    private static final String EXCLUSIONS = "exclusions";
    private static final String SPLIT = ",";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String exclusions = filterConfig.getInitParameter(EXCLUSIONS);
        List<String> excludeList = Arrays.asList(exclusions.split(SPLIT));
        excludeURI.addAll(excludeList);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        String token = null;
        if (!excludeURI.contains(requestURI)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (ZBlogConst.TOKEN_NAME.equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }
            if (!TokenUtil.verifyJwt(token)) {
//                response.sendError(HttpServletResponse.SC_FORBIDDEN, "please log in");
                response.sendRedirect("/view/admin/index.html");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
