package org.example.mvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mvc.controller.Controller;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private RequestMappingHandlerMapping rmhm;

    private List<ViewResolver> viewResolvers;

    @Override
    public void init() throws ServletException {
        log.info("[DispatcherServlet] init started");

        rmhm = new RequestMappingHandlerMapping();
        rmhm.init();

        viewResolvers = Collections.singletonList(new JspViewResolver());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started");
        try {
            Controller handler = rmhm.findHandler(new HandlerKey(RequestMethod.valueOf(request.getMethod()), request.getRequestURI()));            log.info("[DispatcherServlet] handler found: {}", handler.getClass().getName());
            String viewName = handler.handleRequest(request, response);
            log.info("[DispatcherServlet] view found: {}", viewName);

            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveViewName(viewName);
                view.render(new HashMap<>(), request, response);
            }

        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}