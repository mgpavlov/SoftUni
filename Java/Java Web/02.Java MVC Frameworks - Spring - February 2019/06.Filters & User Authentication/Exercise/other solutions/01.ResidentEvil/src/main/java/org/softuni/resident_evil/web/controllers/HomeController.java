package org.softuni.resident_evil.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public ModelAndView homeView(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        System.out.println(roles);
        return this.view("home");
    }

    @GetMapping("/fetch-data")
    public ModelAndView fetchData(){
        return this.view("fetch-table");
    }
}
