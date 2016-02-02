package ru.samvel.jetty.servlets;

import ru.samvel.jetty.db.ControllerDB;
import ru.samvel.jetty.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BayRequestsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerDB controllerDB = new ControllerDB();
        List listElements = controllerDB.selectTable("bay");
        Map<String, Object> data = createPageVariablesMap(listElements);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getBayPage("bay.html", data));
        response.setStatus(HttpServletResponse.SC_OK);
    }
    private static Map<String, Object> createPageVariablesMap(List listElements) {
        Map<String, Object> data = new HashMap<>();
        data.put( "listElements", listElements );
        data.put( "message", "It is my message" );
        return data;
    }
}
