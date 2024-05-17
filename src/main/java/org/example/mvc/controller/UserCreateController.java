package org.example.mvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mvc.model.User;
import org.example.mvc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCreateController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UserCreateController.class);

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // user 추가
        UserRepository.save(new User(request.getParameter("userId"), request.getParameter("name")));
        log.info("[UserCreateController] User created");

        return "redirect:/users"; // 클라이언트에게 /users 로 요청(GET)을 보내도록 리다이렉트를 함
    }
}