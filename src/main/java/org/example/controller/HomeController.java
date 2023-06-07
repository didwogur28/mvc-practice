package org.example.controller;

import org.example.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController implements org.example.controller.Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "home.jsp";
    }
}
