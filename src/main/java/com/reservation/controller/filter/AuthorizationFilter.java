package com.reservation.controller.filter;

import com.reservation.config.UserAuthorization;
import com.reservation.config.WebComponentInitializer;
import com.reservation.model.converter.UserLoginDtoConverter;
import com.reservation.model.dto.UserDto;
import com.reservation.model.enums.Role;
import com.reservation.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);
    private final String loginPageName = "login";
    private final String logoutPageName = "logout";
    private UserLoginDtoConverter userLoginDtoConverter;
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        userLoginDtoConverter = WebComponentInitializer.getInstance().getUserLoginDtoConverter();
        userService = WebComponentInitializer.getInstance().getUserService();

        Map<String, UserAuthorization> usersAuthorization = new HashMap<>();
        ServletContext context = filterConfig.getServletContext();
        context.setAttribute("usersAuthorization", usersAuthorization);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (httpRequest.getSession().getAttribute("role") == null) {
            httpRequest.getSession().setAttribute("role", Role.UNKNOWN);
        }

        String sessionId = httpRequest.getSession().getId();
        Map<String, UserAuthorization> usersAuthorization = (Map<String, UserAuthorization>) httpRequest.getServletContext().getAttribute("usersAuthorization");

        authorizationUser(httpRequest, usersAuthorization, sessionId);
        logout(httpRequest, usersAuthorization, sessionId);

        filterChain.doFilter(servletRequest, servletResponse);
    }


    private void authorizationUser(HttpServletRequest httpRequest, Map<String, UserAuthorization> usersAuthorization, String sessionId) {

        String email = httpRequest.getParameter("email");

        if (httpRequest.getRequestURI().contains(loginPageName) && (email != null)) {

            String foreignUserSession = userSessionOtherSession(email, usersAuthorization, sessionId);

            if (foreignUserSession != null) {
                removeUserAuthorization(usersAuthorization, foreignUserSession);
            }
            createUserAuthorization(httpRequest, usersAuthorization, email);
        }

    }

    private void logout(HttpServletRequest httpRequest, Map<String, UserAuthorization> usersAuthorization, String sessionId) {
        if (httpRequest.getRequestURI().contains(logoutPageName)) {
            removeUserAuthorization(usersAuthorization, sessionId);
            httpRequest.getSession().setAttribute("role", Role.UNKNOWN);
            LOGGER.debug("user logout " + sessionId);
        }
    }

    private String userSessionOtherSession(String email, Map<String, UserAuthorization> usersAuthorization, String sessionId) {
        for (Map.Entry<String, UserAuthorization> stringUserAuthorizationEntry : usersAuthorization.entrySet()) {
            String key = stringUserAuthorizationEntry.getKey();
            UserAuthorization value = stringUserAuthorizationEntry.getValue();
            if (value.getEmail().equals(email) && !sessionId.equals(key)) {
                return key;
            }
        }
        return null;
    }

    private void removeUserAuthorization(Map<String, UserAuthorization> usersAuthorization, String UserSession) {
        usersAuthorization.remove(UserSession);
    }

    private void createUserAuthorization(HttpServletRequest httpRequest,
                                         Map<String, UserAuthorization> usersAuthorization, String email) {

        UserDto userDto = userLoginDtoConverter.convert(httpRequest);

        Role role = userService.receiveUserRole(userDto);
        if (!role.equals(Role.UNKNOWN)) {
            int userId = userService.receiveUserId(userDto);

            UserAuthorization userAuthorization = new UserAuthorization();
            userAuthorization.setEmail(email);
            userAuthorization.setRole(role);
            usersAuthorization.put(httpRequest.getSession().getId(), userAuthorization);
            httpRequest.getSession().setAttribute("role", role);
            httpRequest.getSession().setAttribute("user_id", userId);
            LOGGER.debug("user login: " + userId + " session: " + userId);
        }
    }

    @Override
    public void destroy() {

    }
}
