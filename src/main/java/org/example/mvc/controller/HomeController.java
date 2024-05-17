package org.example.mvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "home.jsp";
    }
}