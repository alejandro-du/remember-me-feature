package com.example;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;

import javax.servlet.http.Cookie;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Alejandro Duarte.
 */
public class AuthService {

    private static final String COOKIE_NAME = "remember-me";
    public static final String SESSION_USERNAME = "username";

    private static SecureRandom random = new SecureRandom();
    private static Map<String, String> rememberedUsers = new HashMap<>();

    public static boolean login(String username, String password) {
        if (username.equals("admin") && password.equals("password")) {
            VaadinSession.getCurrent().setAttribute(SESSION_USERNAME, username);
            return true;
        }

        return false;
    }

    public static void rememberUser(String username) {
        String id = randomString();
        rememberedUsers.put(id, username);
        Cookie cookie = new Cookie(COOKIE_NAME, id);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        VaadinService.getCurrentResponse().addCookie(cookie);
    }

    public static boolean loginRememberedUser() {
        Optional<Cookie> rememberMeCookie = getRememberMeCookie();
        if (rememberMeCookie.isPresent()) {
            String id = rememberMeCookie.get().getValue();
            String username = rememberedUsers.get(id);

            if (username != null) {
                VaadinSession.getCurrent().setAttribute(SESSION_USERNAME, username);
                return true;
            }
        }

        return false;
    }

    private static Optional<Cookie> getRememberMeCookie() {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        return Arrays.stream(cookies).filter(c -> c.getName().equals(COOKIE_NAME)).findFirst();
    }

    private static String randomString() {
        return new BigInteger(130, random).toString(32);
    }

    public static void logOut() {
        Optional<Cookie> cookie = getRememberMeCookie();
        if (cookie.isPresent()) {
            String id = cookie.get().getValue();
            rememberedUsers.remove(id);
            deleteRememberMeCookie();
        }

        VaadinSession.getCurrent().close();
        Page.getCurrent().setLocation("");
    }

    private static void deleteRememberMeCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        VaadinService.getCurrentResponse().addCookie(cookie);
    }

}
