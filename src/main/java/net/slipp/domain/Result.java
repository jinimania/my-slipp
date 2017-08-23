package net.slipp.domain;

public class Result {

  private boolean valid;

  private String errorMessage;

  private Result(final boolean valid, final String errorMessage) {
    this.valid = valid;
    this.errorMessage = errorMessage;
  }

  public boolean isValid() {
    return valid;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public static Result ok() {
    return new Result(true, null);
  }

  public static Result fail(final String errorMessage) {
    return new Result(false, errorMessage);
  }
}
