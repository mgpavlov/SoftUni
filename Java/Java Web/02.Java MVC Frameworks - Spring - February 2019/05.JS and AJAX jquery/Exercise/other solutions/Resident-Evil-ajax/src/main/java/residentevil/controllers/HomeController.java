package residentevil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public ModelAndView renderHomePage(ModelAndView modelAndView) {
        return super.view("index", modelAndView);
    }
}
