package net.slipp.web;

import static net.slipp.web.HttpSessionUtils.USER_SESSION_KEY;
import static net.slipp.web.HttpSessionUtils.getUserFromSession;
import static net.slipp.web.HttpSessionUtils.isLoginUser;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
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

  @GetMapping("/loginForm")
  public String loginForm() {
    return "user/login";
  }

  @PostMapping("/login")
  public String login(final String userId, final String password, final HttpSession session) {
    final User user = userRepository.findByUserId(userId);
    if (user == null) {
      System.out.println("Login Failure!");
      return "redirect:/users/loginForm";
    }
    if (!user.matchPassword(password)) {
      System.out.println("Login Failure!");
      return "redirect:/users/loginForm";
    }
    System.out.println("Login Success!");
    session.setAttribute(USER_SESSION_KEY, user);
    return "redirect:/";
  }
  
  @GetMapping("/logout")
  public String logout(final HttpSession session) {
    session.removeAttribute(USER_SESSION_KEY);
    return "redirect:/";
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
  public String updateForm(final @PathVariable Long id, final Model model,
      final HttpSession session) {
    if (!isLoginUser(session)) {
      return "redirect:/users/loginForm";
    }
    final User sessionUser = getUserFromSession(session);
    if (!sessionUser.matchId(id)) {
      throw new IllegalStateException("You can't update another user");
    }
    model.addAttribute("user", userRepository.findOne(id));
    return "user/updateForm";
  }

  @PostMapping("/{id}")
  public String update(final @PathVariable Long id, final @ModelAttribute User updatedUser,
      final HttpSession session) {
    if (!isLoginUser(session)) {
      return "redirect:/users/loginForm";
    }
    final User sessionUser = getUserFromSession(session);
    if (!sessionUser.matchId(id)) {
      throw new IllegalStateException("You can't update another user");
    }
    final User user = userRepository.findOne(id);
    user.update(updatedUser);
    userRepository.save(user);
    return "redirect:/users";
  }
}
