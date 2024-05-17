package org.example.mvc.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class RedirectView implements View {
    private static final Logger log = LoggerFactory.getLogger(RedirectView.class);

    public static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    private final String name;

    public RedirectView(String name) {
        this.name = name;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        model.forEach(request::setAttribute);
        log.info("RedirectView name: {}", name.substring(DEFAULT_REDIRECT_PREFIX.length()));
        try {
            response.sendRedirect(name.substring(DEFAULT_REDIRECT_PREFIX.length()));
        } catch (AbstractMethodError e) {
            log.error("AbstractMethodError occurred. Class: {}, Method: sendRedirect", response.getClass().getName(), e);
            throw e;
        }
    }
}