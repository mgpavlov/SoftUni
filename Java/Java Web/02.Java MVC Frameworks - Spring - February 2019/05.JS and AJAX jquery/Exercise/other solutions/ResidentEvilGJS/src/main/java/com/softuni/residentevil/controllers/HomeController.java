package com.softuni.residentevil.controllers;

import com.softuni.residentevil.utils.MessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public final class HomeController extends BaseController {

    @Autowired
    public HomeController(final MessageWrapper messageWrapper) {
        super(messageWrapper);
    }

    @GetMapping(value = {"", "/"})
    public ModelAndView rootGet() {
        return super.view("/home");
    }
}
