package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.controller.UserListController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    private static final Logger log = LoggerFactory.getLogger(RequestMappingHandlerMapping.class);

    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    void init() {
        mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
        mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
    }

    public Controller findHandler(HandlerKey handlerKey) {
        log.info("handlerKey: [{}]", handlerKey.toString());
        return mappings.get(handlerKey);
    }
}