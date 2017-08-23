package net.slipp.web;

import static net.slipp.web.HttpSessionUtils.getUserFromSession;
import static net.slipp.web.HttpSessionUtils.isLoginUser;

import javax.servlet.http.HttpSession;
import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    final Question newQuestion = new Question(sessionUser, title, contents);
    questionRepository.save(newQuestion);
    return "redirect:/";
  }

  @GetMapping("/{id}")
  public String show(final @PathVariable Long id, final Model model) {
    model.addAttribute("question", questionRepository.findOne(id));
    return "qna/show";
  }

  @GetMapping("/{id}/form")
  public String updateForm(final @PathVariable Long id, final Model model,
      final HttpSession session) {
    try  {
      final Question question = questionRepository.findOne(id);
      hasPermission(session, question);
      model.addAttribute("question", question);
      return "qna/updateForm";
    } catch (IllegalStateException e) {
      model.addAttribute("errorMessage", e.getMessage());
      return "user/login";
    }
  }

  private void hasPermission(final HttpSession session, final Question question) {
    if (!isLoginUser(session)) {
      throw new IllegalStateException("로그인이 필요합니다.");
    }
    final User loginUser = getUserFromSession(session);
    if (!question.isSameWriter(loginUser)) {
      throw new IllegalStateException("자신이 쓴 글만 수정, 삭제가 가능합니다.");
    }
  }

  @PostMapping("/{id}")
  public String update(final @PathVariable Long id, final String title, final String contents,
      final HttpSession session, final Model model) {
    try  {
      final Question question = questionRepository.findOne(id);
      hasPermission(session, question);
      question.update(title, contents);
      questionRepository.save(question);
      return String.format("redirect:/questions/%d", id);
    } catch (IllegalStateException e) {
      model.addAttribute("errorMessage", e.getMessage());
      return "user/login";
    }
  }

  @DeleteMapping("/{id}")
  public String delete(final @PathVariable Long id, final HttpSession session, final Model model) {
    try  {
      final Question question = questionRepository.findOne(id);
      hasPermission(session, question);
      questionRepository.delete(id);
      return "redirect:/";
    } catch (IllegalStateException e) {
      model.addAttribute("errorMessage", e.getMessage());
      return "user/login";
    }
  }
}
