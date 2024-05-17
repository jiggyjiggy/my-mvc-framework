package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;
import org.example.mvc.controller.UserListController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    private static final Logger log = LoggerFactory.getLogger(RequestMappingHandlerMapping.class);

    private Map<String, Controller> mappings = new HashMap<>();

    void init() {
        mappings.put("/", new HomeController());
        mappings.put("/users", new UserListController());
    }

    public Controller findHandler(String uriPath) {
        log.info("uriPath: [{}]", uriPath);
        return mappings.get(uriPath);
    }
}