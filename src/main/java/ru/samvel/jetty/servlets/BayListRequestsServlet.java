package ru.samvel.jetty.servlets;

import com.google.gson.Gson;
import ru.samvel.jetty.dbService.BayListDBService;
import ru.samvel.jetty.dbService.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class BayListRequestsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, Map<String, String>> fullList = new HashMap<>();
        BayListDBService bayListDBService = new BayListDBService();
        try {
            fullList = bayListDBService.getBayList();
        } catch (DBException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String json = gson.toJson(fullList);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(json);
    }

}
