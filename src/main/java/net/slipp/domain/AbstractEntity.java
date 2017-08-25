package net.slipp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

  @Id
  @GeneratedValue
  @JsonProperty
  private Long id;

  @CreatedDate
  private LocalDateTime createDate;

  @LastModifiedDate
  private LocalDateTime modifiedDate;

  public Long getId() {
    return id;
  }

  public String getFormattedCreateDate() {
    return getFormattedDate(createDate, "yyyy.MM.dd HH:mm:ss");
  }

  public String getFormattedModifiedDate() {
    return getFormattedDate(modifiedDate, "yyyy.MM.dd HH:mm:ss");
  }

  private String getFormattedDate(final LocalDateTime dateTime, final String format) {
    if (dateTime == null) {
      return "";
    }
    return dateTime.format(DateTimeFormatter.ofPattern(format));
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final AbstractEntity that = (AbstractEntity) o;

    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "AbstractEntity{" +
        "id=" + id +
        ", createDate=" + createDate +
        ", modifiedDate=" + modifiedDate +
        '}';
  }
}
