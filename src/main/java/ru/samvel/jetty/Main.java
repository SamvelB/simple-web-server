package ru.samvel.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.samvel.jetty.servlets.FormRequestsServlet;
import ru.samvel.jetty.servlets.IndexRequestsServlet;
import ru.samvel.jetty.servlets.PageRequestsServlet;
import ru.samvel.jetty.servlets.SamvelRequestsServlet;



public class Main {
    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);

        context.addServlet(new ServletHolder(new IndexRequestsServlet()), "/");
        context.addServlet(new ServletHolder(new FormRequestsServlet()), "/form");
        context.addServlet(new ServletHolder(new PageRequestsServlet()), "/page");
        context.addServlet(new ServletHolder(new SamvelRequestsServlet()), "/samvel");

        server.start();
        server.join();

    }
}
