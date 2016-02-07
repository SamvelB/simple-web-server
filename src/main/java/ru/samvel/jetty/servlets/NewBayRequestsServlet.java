package ru.samvel.jetty.servlets;

import ru.samvel.jetty.dbService.BayListDBService;
import ru.samvel.jetty.dbService.DBException;
import ru.samvel.jetty.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class NewBayRequestsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("error.html"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String amount = request.getParameter("amount");

        Map<String, String> baySet = new HashMap<>();
        baySet.put(name, amount);

        BayListDBService bayListDBService = new BayListDBService();
        for (Map.Entry<String, String> entry : baySet.entrySet()){
            try {
                bayListDBService.addBayList(entry.getKey(), entry.getValue());
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
    }


}
