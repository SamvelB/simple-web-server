package ru.samvel.jetty.servlets;

import ru.samvel.jetty.accounts.AccountService;
import ru.samvel.jetty.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BayRequestsServlet extends HttpServlet {

    private final AccountService accountService;

    public BayRequestsServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ControllerDB controllerDB = new ControllerDB();
        //List listElements = controllerDB.selectTable("bay");
        accountService.addSession(request.getSession().getId(), profile);

        Map<String, Object> data = new HashMap<>();
        data.put( "message", "It is my message" );
        data.put( "message1", "ISad sadasd as asd" );

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getBayPage("bay.html", data));
        response.setStatus(HttpServletResponse.SC_OK);
    }
    private static Map<String, Object> createPageVariablesMap(List listElements) {
        //Map<String, Object> data = new HashMap<>();
        //data.put( "listElements", listElements );
        //data.put( "message", "It is my message" );
        //return data;
    }
}
