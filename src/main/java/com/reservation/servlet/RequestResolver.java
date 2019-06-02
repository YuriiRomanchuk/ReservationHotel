package com.reservation.servlet;

import com.reservation.config.WebComponentInitializer;
import com.reservation.view.RedirectViewModel;
import com.reservation.view.View;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class RequestResolver {

    private static final String VIEW_ATTRIBUTE = "VIEW_ATTRIBUTE";

    private Map<String, Function<HttpServletRequest, View>> getControllers = new HashMap<>();
    private Map<String, Function<HttpServletRequest, View>> postControllers = new HashMap<>();

    public RequestResolver(WebComponentInitializer webComponentInitializer) {

        getControllers.put("/index", r -> webComponentInitializer.getWelcomeController().showIndexPage());

        getControllers.put("/registration-form", r -> webComponentInitializer.getUserController().showRegistrationPage());
        getControllers.put("/login", r -> webComponentInitializer.getUserController().showUserLoginPage());
        getControllers.put("/admin-personal-area", r -> webComponentInitializer.getUserController().showAdminPersonalArea());
        getControllers.put("/user-personal-area", r -> webComponentInitializer.getUserController().showUserPersonalArea(webComponentInitializer.getUserDtoConverter().convertFromRequestForUserId(r)));
        getControllers.put("/logout", r -> webComponentInitializer.getUserController().logout());
        getControllers.put("/admin-add-room", r -> webComponentInitializer.getRoomController().showAddRoomPage());
        getControllers.put("/user-request-room", r -> webComponentInitializer.getRequestRoomController().showAddRequestRoomPage());

        postControllers.put("/login", r -> webComponentInitializer.getUserController().loginUser(webComponentInitializer.getUserLoginDtoConverter().convert(r)));
        postControllers.put("/registration-form", r -> webComponentInitializer.getUserController().createUser(webComponentInitializer.getUserDtoConverter().convert(r)));
        postControllers.put("/change_language", r -> webComponentInitializer.getChangeLanguageController().changeLanguage());
        postControllers.put("/admin-add-room", r -> webComponentInitializer.getRoomController().createRoom(webComponentInitializer.getRoomDtoConverter().convert(r)));
        getControllers.put("/user-request-room", r -> webComponentInitializer.getRequestRoomController().createRoom(webComponentInitializer.getRequestRoomDtoConverter().convert(r)));

    }

    public void resolveGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reference(request, response, getControllers);
    }

    public void resolvePostRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reference(request, response, postControllers);
    }

    private void reference(HttpServletRequest request, HttpServletResponse response, Map<String, Function<HttpServletRequest, View>> controller) throws IOException, ServletException {
        try {
            reference(getView(request, controller), request, response);
        } catch (Exception e) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            request.setAttribute("error", e);
            requestDispatcher.forward(request, response);
        }
    }

    private void reference(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (view instanceof RedirectViewModel) {
            request.getSession().setAttribute(VIEW_ATTRIBUTE, view.getView());
            response.sendRedirect(request.getContextPath() + "/main/" + view.getPageUrl());
            /*response.sendRedirect(view.getPageUrl());*/
        } else if (view != null) {
            view.getParameters().forEach(request::setAttribute);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/" + view.getPageUrl());
            requestDispatcher.forward(request, response);
        }
    }

    private View getView(HttpServletRequest request, Map<String, Function<HttpServletRequest, View>> sourceController) {

        String requestURI = receiveRequestURI(request);
        View originView = (View) request.getSession().getAttribute(VIEW_ATTRIBUTE);
        request.getSession().removeAttribute(VIEW_ATTRIBUTE);

        View destinationView = Optional.ofNullable(sourceController.get(requestURI)).map(f -> f.apply(request)).orElse(null);
        if (originView != null && destinationView != null) {
            originView.getParameters().forEach(destinationView::addParameter);
        }
        return destinationView;
    }

    private String receiveRequestURI(HttpServletRequest request) {
        String requestURI = request.getRequestURI().replace(request.getContextPath() + "/main", "");
        String[] splitURI = requestURI.split("/");
        String lastElement = splitURI.length > 0 ? splitURI[splitURI.length - 1] : "";
        return lastElement.matches("\\d+") ? requestURI.replace(lastElement, "{id}") : "/" + lastElement;
    }
}
