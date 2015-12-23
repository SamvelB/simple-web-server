package ru.samvel.jetty.servlets;

import ru.samvel.jetty.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PageRequestsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);
        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        if(request.getParameter("name") != null){
            pageVariables.put("name", request.getParameter("name").toString());
            System.out.println(request.getParameter("name").toString());
        }
        if(request.getParameter("lastName") != null){
            pageVariables.put("lastName", request.getParameter("lastName").toString());
        }
        return pageVariables;
    }
}
