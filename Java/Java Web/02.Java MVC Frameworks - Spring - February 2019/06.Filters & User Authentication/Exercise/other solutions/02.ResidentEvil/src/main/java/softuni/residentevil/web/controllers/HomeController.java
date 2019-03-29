package softuni.residentevil.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController extends BaseController {

  @GetMapping("/error")
  public ModelAndView wrong() {
    return super.view("error/wrong");
  }

  @GetMapping({"/", "/home"})
  public ModelAndView home(Principal principal, ModelAndView modelAndView) {
    if (principal != null){
      modelAndView.addObject("username", principal.getName());
    }
    return super.view("home", modelAndView);
  }


}
