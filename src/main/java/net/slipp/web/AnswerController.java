package net.slipp.web;

import static net.slipp.web.HttpSessionUtils.getUserFromSession;
import static net.slipp.web.HttpSessionUtils.isLoginUser;

import javax.servlet.http.HttpSession;
import net.slipp.domain.Answer;
import net.slipp.domain.AnswerRepository;
import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {

  private final QuestionRepository questionRepository;

  private final AnswerRepository answerRepository;

  @Autowired
  public AnswerController(final QuestionRepository questionRepository,
      final AnswerRepository answerRepository) {
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  @PostMapping("")
  public String create(final @PathVariable Long questionId, final String contents,
      final HttpSession session) {
    if (!isLoginUser(session)) {
      return "redirect:/users/loginForm";
    }
    final User loginUser = getUserFromSession(session);
    final Question question = questionRepository.findOne(questionId);
    final Answer answer = new Answer(loginUser,  question, contents);
    answerRepository.save(answer);
    return String.format("redirect:/questions/%d", questionId);
  }
}
