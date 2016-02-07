package ru.samvel.jetty.dbService;

import ru.samvel.jetty.dbService.dao.BayListDAO;
import ru.samvel.jetty.dbService.dataSets.BayListDataSet;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;


public class BayListDBService {
    private final Connection connection;

    public BayListDBService() {
        this.connection = getMysqlConnection();
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
            //dao.createTable();
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
        //try {
        //dao.dropTable();
        //} catch (SQLException e) {
        //throw new DBException(e);
        //}
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            String url = "&useUnicode=true&characterEncoding=UTF-8";
            Connection connection = DriverManager.getConnection(url.toString());

            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}

