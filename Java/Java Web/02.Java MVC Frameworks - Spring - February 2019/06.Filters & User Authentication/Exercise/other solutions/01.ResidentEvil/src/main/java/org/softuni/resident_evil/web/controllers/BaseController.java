package org.softuni.resident_evil.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public class BaseController {

    final ModelAndView view(String viewName) {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("base-layout");
        modelAndView.addObject("view", viewName);
        return modelAndView;
    }


    final ModelAndView redirect(String redirectUrl) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + redirectUrl);
        return modelAndView;
    }


}
