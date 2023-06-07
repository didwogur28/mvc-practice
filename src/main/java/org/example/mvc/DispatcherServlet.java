package org.example.mvc;

import org.example.controller.Controller;
import org.example.controller.RequestMappingHandlerMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")    // 어떤 경로를 입력해도 해당 Servlet이 실행
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private RequestMappingHandlerMapping rmhm;

    @Override
    public void init() throws ServletException {
        rmhm = new RequestMappingHandlerMapping();
        rmhm.init();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("[DispatcherServlet] Service started.");

        try {

            Controller handler = rmhm.findHandler(request.getRequestURI());
            String viewName = handler.handleRequest(request, response);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
            requestDispatcher.forward(request, response);

        } catch (Exception e) {
            log.error("exception occurred: [{}] ", e.getMessage(), e);
        }
    }
}
