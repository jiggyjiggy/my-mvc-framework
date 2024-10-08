package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.ForwardController;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.controller.UserCreateController;
import org.example.mvc.controller.UserListController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping {
    private static final Logger log = LoggerFactory.getLogger(RequestMappingHandlerMapping.class);

    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    void init() {
        mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
        mappings.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("/user/form"));
        mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());
    }

    public Controller findHandler(HandlerKey handlerKey) {
        log.info("handlerKey: [{}]", handlerKey.toString());
        return mappings.get(handlerKey);
    }
}