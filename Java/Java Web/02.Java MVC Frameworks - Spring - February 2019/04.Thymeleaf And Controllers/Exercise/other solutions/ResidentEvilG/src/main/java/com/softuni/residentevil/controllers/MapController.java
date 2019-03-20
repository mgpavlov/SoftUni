package com.softuni.residentevil.controllers;

import com.softuni.residentevil.utils.MessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/map")
public final class MapController extends BaseController {

    @Autowired
    public MapController(final MessageWrapper messageWrapper) {
        super(messageWrapper);
    }

    @GetMapping(value = {"", "/"})
    public ModelAndView rootGet() {
        return super.view("/map/view");
    }
}
