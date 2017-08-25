package net.slipp.web;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

  private final UserRepository userRepository;

  @Autowired
  public ApiUserController(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/{id}")
  public User show(final @PathVariable Long id) {
    return userRepository.findOne(id);
  }
}
