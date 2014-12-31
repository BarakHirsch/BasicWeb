package helpers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class UserHelper {

    public static String getUserName(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if ((c.getName().equals("UserName"))) {
                return c.getValue();
            }
        }
        return "Guest";
    }

    public static boolean isLogedIn(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if ((c.getName().equals("UserName"))) {
                return true;
            }
        }
        return false;
    }
}
