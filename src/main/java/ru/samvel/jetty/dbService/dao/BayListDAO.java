package ru.samvel.jetty.dbService.dao;


import ru.samvel.jetty.dbService.dataSets.BayListDataSet;
import ru.samvel.jetty.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class BayListDAO {

    private Executor executor;

    public BayListDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public BayListDataSet getBayListId(long id) throws SQLException {
        return executor.execQuery("select * from bay where id=" + id, result -> {
            result.next();
            return new BayListDataSet(result.getLong(1), result.getString(2), result.getString(3));
        });
    }

    public long getBayListName(String bayName) throws SQLException {
        return executor.execQuery("select * from bay where bayName='" + bayName + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }
    public Map<Integer, Map<String, String>> getBayList() throws SQLException {
        Map<Integer, Map<String, String>> allDataSet = new HashMap<>();
        executor.execQuery("select * from bay", result -> {
            int i = 0;
            while (result.next()) {
                Map<String, String> dataSet = new HashMap<>();
                dataSet.put("bayName", result.getString("bayName"));
                dataSet.put("amount", result.getString("amount"));
                allDataSet.put(Integer.valueOf(result.getString("ID")), dataSet);
                i++;
            }
            return allDataSet;
        });
        return allDataSet;
    }

    public void insertBayList(String bayName, String amount) throws SQLException {
        executor.execUpdate("insert into bay (bayName, amount) values ('" + bayName + "', '" + amount + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists bay (id bigint auto_increment, bayName varchar(256), amount varchar(256), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table bay");
    }
}
