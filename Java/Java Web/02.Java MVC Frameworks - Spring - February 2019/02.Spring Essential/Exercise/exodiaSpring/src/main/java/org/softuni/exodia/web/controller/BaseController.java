package org.softuni.exodia.web.controller;

import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;


public abstract class BaseController {
    protected boolean isLoggedIn(HttpSession httpSession) {
        return httpSession.getAttribute("username") != null;
    }

    protected ModelAndView view(String view, ModelAndView modelAndView) {
        modelAndView.setViewName(view);

        return modelAndView;
    }

    protected ModelAndView view(String view) {
        return this.view(view, new ModelAndView());
    }


}
