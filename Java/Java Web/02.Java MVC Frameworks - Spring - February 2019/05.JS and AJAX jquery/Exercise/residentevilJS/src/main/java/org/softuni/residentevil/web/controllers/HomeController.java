package org.softuni.residentevil.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {
    public ModelAndView home() {
        return super.view("/index");
    }
}
