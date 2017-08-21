package net.slipp.web;

import static net.slipp.web.HttpSessionUtils.getUserFromSession;
import static net.slipp.web.HttpSessionUtils.isLoginUser;

import javax.servlet.http.HttpSession;
import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions")
public class QuestionController {

  private final QuestionRepository questionRepository;

  @Autowired
  public QuestionController(final QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  @GetMapping("/form")
  public String form(final HttpSession session) {
    if (!isLoginUser(session)) {
      return "redirect:/users/loginForm";
    }
    return "qna/form";
  }
  
  @PostMapping("")
  public String create(final String title, final String contents, final HttpSession session) {
    if (!isLoginUser(session)) {
      return "redirect:/users/loginForm";
    }
    final User sessionUser = getUserFromSession(session);
    final Question newQuestion = new Question(sessionUser.getUserId(), title, contents);
    questionRepository.save(newQuestion);
    return "redirect:/";
  }
}
