package org.example.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {

    private Map<String, Controller> mappings = new HashMap<>();

    public void init() {
        mappings.put("/", new HomeController());
    }

    public Controller findHandler(String urlPath) {
        return mappings.get(urlPath);
    }

}
