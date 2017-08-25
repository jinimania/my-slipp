package net.slipp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {

  @Column(nullable = false, length = 20, unique = true)
  @JsonProperty
  private String userId;

  @JsonIgnore
  private String password;

  @JsonProperty
  private String name;

  @JsonProperty
  private String email;

  public String getUserId() {
    return userId;
  }

  public void setUserId(final String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public void update(final User newUser) {
    this.password = newUser.password;
    this.name = newUser.name;
    this.email = newUser.email;
  }

  public boolean matchPassword(final String newPassword) {
    return newPassword != null && newPassword.equals(password);
  }

  public boolean matchId(final Long newId) {
    return newId != null && newId.equals(getId());
  }

  @Override
  public String toString() {
    return "User{" + super.toString() + ", userId=" + userId + ", password=" + password + ","
        + "name=" + name + ", email=" + email + "}";
  }
}
