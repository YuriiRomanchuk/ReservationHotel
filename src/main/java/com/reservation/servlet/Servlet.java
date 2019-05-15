package com.reservation.servlet;

import com.reservation.config.WebComponentInitializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    private RequestResolver requestResolver;

    @Override
    public void init() throws ServletException {
        requestResolver = WebComponentInitializer.getInstance().getRequestResolver();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestResolver.resolvePostRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestResolver.resolveGetRequest(req, resp);
    }
}


