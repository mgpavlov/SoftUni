package org.softuni.residentevil.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController extends BaseController {
    @GetMapping("/")
    public ModelAndView renderIndexPage(Principal principal, ModelAndView modelAndView) {
        modelAndView.addObject("principal", principal);
        return super.view("/index", modelAndView);
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView renderHomePage(Principal principal, ModelAndView modelAndView) {
        modelAndView.addObject("principal", principal);

        return super.view("/index", modelAndView);
    }
}
