package net.slipp.domain;

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
public class Answer {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
  private User writer;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
  private Question question;

  @Lob
  private String contents;

  private LocalDateTime createDate;

  public Answer() {
  }

  public Answer(final User writer, final Question question, final String contents) {
    this.writer = writer;
    this.question = question;
    this.contents = contents;
    this.createDate = LocalDateTime.now();
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

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(final LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public String getFormattedCreateDate() {
    if (createDate == null) {
      return "";
    }
    return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Answer answer = (Answer) o;

    return id.equals(answer.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "Answer{" +
        "id=" + id +
        ", writer=" + writer +
        ", contents='" + contents + '\'' +
        ", createDate=" + createDate +
        '}';
  }
}
