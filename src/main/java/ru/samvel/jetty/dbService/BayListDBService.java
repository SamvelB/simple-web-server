package ru.samvel.jetty.dbService;

import org.h2.jdbcx.JdbcDataSource;
import ru.samvel.jetty.dbService.dao.BayListDAO;
import ru.samvel.jetty.dbService.dataSets.BayListDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;


public class BayListDBService {
    private final Connection connection;

    public BayListDBService() {
        this.connection = getH2Connection();
    }

    public BayListDataSet getBayListID(long id) throws DBException {
        try {
            return (new BayListDAO(connection).getBayListId(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public Map<Integer, Map<String, String>> getBayList() throws DBException {
        try {
            return (new BayListDAO(connection).getBayList());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public long addBayList(String bayName, String amount) throws DBException {
        try {
            connection.setAutoCommit(false);
            BayListDAO dao = new BayListDAO(connection);
            dao.createTable();
            dao.insertBayList(bayName, amount);
            connection.commit();
            return dao.getBayListName(bayName);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void cleanUp() throws DBException {
        BayListDAO dao = new BayListDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

/*    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


    public static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "tully";
            String pass = "tully";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
