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
import ru.samvel.jetty.dbService.dataSets.BayListDataSet;
import ru.samvel.jetty.servlets.*;
import ru.samvel.jetty.servlets.account.SessionsServlet;
import ru.samvel.jetty.servlets.account.UsersServlet;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<String> bayNames = new ArrayList<>();
        BayListDBService bayListDBService = new BayListDBService();
        try {
            bayNames.add("Samvel");
            bayNames.add("Vasya");
            bayNames.add("Ivan");

            long bayListId = bayListDBService.addBayList("название", "14 шт");
            BayListDataSet bayListDataSet = bayListDBService.getBayListID(bayListId);

            for (int i=0; i<=2; i++){
                bayListDBService.addBayList(bayNames.get(i), "14 шт");
            }
            System.out.println("Added user id: " + bayListId);
            System.out.println("User data set: " + bayListDataSet);

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
