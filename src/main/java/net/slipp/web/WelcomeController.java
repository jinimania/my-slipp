package net.slipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

  @GetMapping("/helloWorld")
  public String welcome(final String name, final int age, final Model model) {
    System.out.println("name: " + name + " age: " + age);
    model.addAttribute("name", name);
    model.addAttribute("age", age);
    return "welcome";
  }
}
