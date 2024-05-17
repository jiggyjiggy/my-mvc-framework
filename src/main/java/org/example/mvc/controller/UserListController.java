package org.example.mvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class UserListController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("users", List.of());
        return "/user/list.jsp";
    }
}