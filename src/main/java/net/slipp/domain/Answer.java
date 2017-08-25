package net.slipp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Answer extends AbstractEntity {

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
  @JsonProperty
  private User writer;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
  @JsonProperty
  private Question question;

  @Lob
  @JsonProperty
  private String contents;

  public Answer() {
  }

  public Answer(final User writer, final Question question, final String contents) {
    this.writer = writer;
    this.question = question;
    this.contents = contents;
  }

  public User getWriter() {
    return writer;
  }

  public void setWriter(final User writer) {
    this.writer = writer;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(final Question question) {
    this.question = question;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(final String contents) {
    this.contents = contents;
  }

  public boolean isSameWriter(final User loginUser) {
    return loginUser.equals(this.writer);
  }

  @Override
  public String toString() {
    return "Answer{" +
        super.toString() +
        ", writer=" + writer +
        ", contents='" + contents +
        '}';
  }
}
