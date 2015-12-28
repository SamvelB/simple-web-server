package ru.samvel.jetty.servlets;

import ru.samvel.jetty.db.ControllerDB;
import ru.samvel.jetty.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class BayRequestsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tableName = "bay";
        Map<Integer, Map<String,String>> mapPetList = null;
        ControllerDB controllerDB = new ControllerDB();
        mapPetList = controllerDB.selectTable(tableName);
        String saamvel = mapPetList.get(0).get("NAME");
        System.out.println("mapPetList: " + mapPetList);
        System.out.println("saamvel: " + saamvel);

        HashMap<String, String> test1 = new HashMap<String, String>();
        HashMap<String, String> test2 = new HashMap<String, String>();
        Map root = new HashMap();
        test1.put("one", "1");
        test1.put("two", "2");
        test1.put("three", "3");
        root.put("hello", mapPetList);

/*        test2.put("one", "4");
        test2.put("two", "5");
        test2.put("three", "6");
        root.put("hello2", test2);*/

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getBayPage("bay.html", root));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
        String tableName = "bay";
        Map<Integer, Map<String,String>> mapPetList = null;
        ControllerDB controllerDB = new ControllerDB();
        mapPetList = controllerDB.selectTable(tableName);
        System.out.println(mapPetList);
*/






        Map<String, Object> pageVariables = createPageVariablesMap(request);
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");

        if (false) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            pageVariables.put("name", name);
            pageVariables.put("lastName", lastName);
            response.setStatus(HttpServletResponse.SC_OK);
        }

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("bay.html", pageVariables));
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
