package com.Picloud.web.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/ImageInterceptor")
public class ImageRedirectFilter implements Filter {
        
        public void doFilter(ServletRequest req, ServletResponse res,
                        FilterChain chain) throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                // 禁止缓存
                response.setHeader("Cache-Control", "no-store");
                response.setHeader("Pragrma", "no-cache");
                response.setDateHeader("Expires", 0);
                // 链接来源地址
                String referer = request.getHeader("referer");
                System.out.println("refer is  " + referer);
                if (referer == null
                                || !referer.contains(request.getServerName())) {
                        System.out.println("jinlai");
                        // 如果链接地址来自其他网站，则返回错误图片
                        request.getRequestDispatcher("/resources/images/error.jpg").forward(
                                        request, response);
                } else {
                        // 图片正常显示
                        chain.doFilter(request, response);
                }
        }

        @Override
        public void destroy() {
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
                
        }
}
