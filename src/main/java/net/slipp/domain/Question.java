package net.slipp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Question {

  @Id
  @GeneratedValue
  @JsonProperty
  private Long id;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
  @JsonProperty
  private User writer;

  @JsonProperty
  private String title;

  @Lob
  @JsonProperty
  private String contents;

  private LocalDateTime createDate;

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
    this.createDate = LocalDateTime.now();
  }

  public Long getId() {
    return id;
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

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(final LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(final List<Answer> answers) {
    this.answers = answers;
  }

  public String getFormattedCreateDate() {
    if (createDate == null) {
      return "";
    }
    return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
  }

  public void update(final String title, final String contents) {
    this.title = title;
    this.contents = contents;
  }

  public boolean isSameWriter(final User loginUser) {
    return this.writer.equals(loginUser);
  }
}
