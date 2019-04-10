package org.softuni.onlinegrocery.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

@Component
public class GreetingInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("Called after handler method request completion,"
                + " before rendering the view");
        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            LocalTime time = LocalTime.now();
            int hrs = time.getHour();
            if (hrs >= 0 && hrs <= 12) {
                modelAndView.addObject("greeting", "Good morning ");
            } else if (hrs > 12 && hrs <= 17) {
                modelAndView.addObject("greeting", "Good afternoon ");
            } else {
                modelAndView.addObject("greeting", "Good evening ");
            }
        }
    }
}