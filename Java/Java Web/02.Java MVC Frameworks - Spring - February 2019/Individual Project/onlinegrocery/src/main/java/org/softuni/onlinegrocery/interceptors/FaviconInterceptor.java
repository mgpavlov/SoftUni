package org.softuni.onlinegrocery.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FaviconInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String link = "https://res.cloudinary.com/mgpavlov/image/upload/v1554912174/icons8-shopping-cart-96.png";

        if (modelAndView != null) {
            modelAndView.addObject("favicon", link);
        }
    }
}
