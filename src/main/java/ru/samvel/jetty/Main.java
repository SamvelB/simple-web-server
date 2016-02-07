package ru.samvel.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.samvel.jetty.accounts.AccountService;
import ru.samvel.jetty.accounts.UserProfile;
import ru.samvel.jetty.dbService.BayListDBService;
import ru.samvel.jetty.dbService.DBException;
import ru.samvel.jetty.servlets.*;
import ru.samvel.jetty.servlets.account.SessionsServlet;
import ru.samvel.jetty.servlets.account.UsersServlet;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws Exception {

        Map<String, String> bayNames = new HashMap<>();
        //bayNames.put("Samvel", "1 шт");
        //bayNames.put("Vasya", "2 шт");
        //bayNames.put("Ivan", "3 шт");

        BayListDBService bayListDBService = new BayListDBService();
        try {
            for (Map.Entry<String, String> entry : bayNames.entrySet()){
                bayListDBService.addBayList(entry.getKey(), entry.getValue());
            }

            BayListDBService bayListDataSetAll = new BayListDBService();
            System.out.println("User data set ALL: " + bayListDataSetAll.getBayList());

            bayListDBService.cleanUp();
        } catch (DBException e) {
            e.printStackTrace();
        }
        //////////////////////////////////////////////////////////////////////

        AccountService accountService = new AccountService();
        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new IndexRequestsServlet()), "/index");
        context.addServlet(new ServletHolder(new MainRequestsServlet()), "/main");

        context.addServlet(new ServletHolder(new BayRequestsServlet()), "/bay");
        context.addServlet(new ServletHolder(new BayListRequestsServlet()), "/baylist");
        context.addServlet(new ServletHolder(new NewBayRequestsServlet()), "/newbay");


        context.addServlet(new ServletHolder(new FormRequestsServlet()), "/form");
        context.addServlet(new ServletHolder(new PageRequestsServlet()), "/page");
        context.addServlet(new ServletHolder(new SamvelRequestsServlet()), "/samvel");

        context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("src/main/templates/static");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        server.join();

    }
}
