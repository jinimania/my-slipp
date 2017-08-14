package net.slipp.web;

public class User {

  private String userId;
  private String password;
  private String name;
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

  @Override
  public String toString() {
    return "User{userId=" + userId + ", password=" + password + ", name=" + name + ", email="
        + email + "}";
  }
}
