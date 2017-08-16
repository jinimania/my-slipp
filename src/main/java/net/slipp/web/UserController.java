package net.slipp.web;

import java.util.ArrayList;
import java.util.List;
import net.slipp.domain.User;
import net.slipp.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

  private List<User> users = new ArrayList<>();

  private final UserRepository userRepository;

  @Autowired
  public UserController(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/form")
  public String form() {
    return "user/form";
  }

  @PostMapping("")
  public String create(final User user) {
    System.out.println("user: " + user);
    users.add(user);
    userRepository.save(user);
    return "redirect:/users";
  }

  @GetMapping("")
  public String list(final Model model) {
    model.addAttribute("users", userRepository.findAll());
    return "user/list";
  }

  @GetMapping("/{id}/form")
  public String updateForm(final @PathVariable Long id, final Model model) {
    model.addAttribute("user", userRepository.findOne(id));
    return "/user/updateForm";
  }

  @PostMapping("/{id}")
  public String update(final @PathVariable Long id, final @ModelAttribute User newUser) {
    final User user = userRepository.findOne(id);
    user.update(newUser);
    userRepository.save(user);
    return "redirect:/users";
  }
}
