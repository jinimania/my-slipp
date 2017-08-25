package net.slipp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Question extends AbstractEntity {

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
  @JsonProperty
  private User writer;

  @JsonProperty
  private String title;

  @Lob
  @JsonProperty
  private String contents;

  @JsonProperty
  private Integer countOfAnswer = 0;

  @OneToMany(mappedBy = "question")
  @OrderBy("id DESC")
  @JsonIgnore
  private List<Answer> answers;

  public Question() {
  }

  public Question(final User writer, final String title, final String contents) {
    this.writer = writer;
    this.title = title;
    this.contents = contents;
  }

  public User getWriter() {
    return writer;
  }

  public void setWriter(final User writer) {
    this.writer = writer;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(final String contents) {
    this.contents = contents;
  }

  public Integer getCountOfAnswer() {
    return countOfAnswer;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(final List<Answer> answers) {
    this.answers = answers;
  }

  public void update(final String title, final String contents) {
    this.title = title;
    this.contents = contents;
  }

  public boolean isSameWriter(final User loginUser) {
    return this.writer.equals(loginUser);
  }

  public void addAnswer() {
    this.countOfAnswer += 1;
  }

  public void minusAnswer() {
    this.countOfAnswer -= 1;
  }
}
