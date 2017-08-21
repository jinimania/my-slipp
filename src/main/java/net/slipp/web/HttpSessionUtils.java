package net.slipp.web;

import javax.servlet.http.HttpSession;
import net.slipp.domain.User;

public class HttpSessionUtils {

  public static final String USER_SESSION_KEY = "sessionUser";

  public static boolean isLoginUser(final HttpSession session) {
    final Object sessionUser = session.getAttribute(USER_SESSION_KEY);
    return sessionUser != null;
  }

  public static User getUserFromSession(final HttpSession session) {
    if (!isLoginUser(session)) {
      return null;
    }
    return (User) session.getAttribute(USER_SESSION_KEY);
  }
}
