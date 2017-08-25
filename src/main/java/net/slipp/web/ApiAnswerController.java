package net.slipp.web;

import static net.slipp.web.HttpSessionUtils.getUserFromSession;
import static net.slipp.web.HttpSessionUtils.isLoginUser;

import javax.servlet.http.HttpSession;
import net.slipp.domain.Answer;
import net.slipp.domain.AnswerRepository;
import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.Result;
import net.slipp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {

  private final QuestionRepository questionRepository;

  private final AnswerRepository answerRepository;

  @Autowired
  public ApiAnswerController(final QuestionRepository questionRepository,
      final AnswerRepository answerRepository) {
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  @PostMapping("")
  public Answer create(final @PathVariable Long questionId, final String contents,
      final HttpSession session) {
    if (!isLoginUser(session)) {
      return null;
    }
    final User loginUser = getUserFromSession(session);
    final Question question = questionRepository.findOne(questionId);
    final Answer answer = new Answer(loginUser, question, contents);
    question.addAnswer();
    return answerRepository.save(answer);
  }

  @DeleteMapping("/{id}")
  public Result delete(final @PathVariable Long questionId, final @PathVariable Long id,
      final HttpSession session) {
    if (!isLoginUser(session)) {
      return Result.fail("로그인해야 합니다.");
    }
    final Answer answer = answerRepository.findOne(id);
    final User loginUser = getUserFromSession(session);
    if (!answer.isSameWriter(loginUser)) {
      Result.fail("자신의 글만 삭제할 수 있습니다.");
    }
    answerRepository.delete(id);
    final Question question = questionRepository.findOne(questionId);
    question.minusAnswer();
    questionRepository.save(question);
    return Result.ok();
  }
}
