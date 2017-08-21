package net.slipp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {

  @Id
  @GeneratedValue
  private Long id;

  private String writer;

  private String title;

  private String contents;

  public Question() {
  }

  public Question(final String writer, final String title, final String contents) {
    this.writer = writer;
    this.title = title;
    this.contents = contents;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(final String writer) {
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
}
