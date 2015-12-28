package ru.samvel.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.samvel.jetty.servlets.*;


public class Main {
    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);

        context.addServlet(new ServletHolder(new IndexRequestsServlet()), "/");
        context.addServlet(new ServletHolder(new FormRequestsServlet()), "/form");
        context.addServlet(new ServletHolder(new PageRequestsServlet()), "/page");
        context.addServlet(new ServletHolder(new LoginRequestsServlet()), "/login");
        context.addServlet(new ServletHolder(new LoginCheckRequestsServlet()), "/login-check");
        context.addServlet(new ServletHolder(new ErrorRequestsServlet()), "/error");
        context.addServlet(new ServletHolder(new BayRequestsServlet()), "/bay");

        server.start();
        server.join();

    }
}
