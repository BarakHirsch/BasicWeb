package helpers;

import static helpers.Constants.USER_ATTRIBUTE;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import models.User;

public class UserHelper {

    public static User getUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);

        if (user != null) {
            return new User(user.getName(), true);
        }

        return new User("Guest", false);
    }

    public static void LoadUserToSession(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);
        if (user != null) {
            return;
        }

        Cookie[] cookies = request.getCookies();
        
        if (cookies == null) {
            return;
        }

        for (Cookie c : cookies) {
            if ((c.getName().equals(USER_ATTRIBUTE))) {

                user = new User();

                user.setName(c.getValue());
            }
        }
    }

    public static void LoadUserToSession(HttpServletRequest request, User user) {

        request.getSession().setAttribute(USER_ATTRIBUTE, user);
    }

    public static void LogoutUser(HttpServletRequest request) {
        request.getSession().setAttribute(USER_ATTRIBUTE, null);

        Cookie[] cookies = request.getCookies();

        for (Cookie c : cookies) {
            if ((c.getName().equals(USER_ATTRIBUTE))) {
                c.setMaxAge(0);
                break;
            }
        }
    }
}
