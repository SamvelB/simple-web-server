package ru.samvel.jetty.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queries{

    Connect connect = new Connect();

    //Map<Integer, Map<String, String>> mapPet = new HashMap<>();
    //Map<Integer, Map<String, String>> mapPetLife = new HashMap<>();
    List listElements = new ArrayList();

    ResultSet result = null;

    // обработк SELECT запроса
    public List sqlRequestSQL(String sql, String table) {
        if (connect != null) {
            try {
                // Установка соединения
                connect.setConnection();
                Connection connection = connect.getConnection();
                Statement statement = connection.createStatement();

                // Ответ
                result = statement.executeQuery(sql);

                // Парс ответа из базы
                return parsRespond(table);
            }
            catch (Exception ex){
                System.out.println("-- Ошибка: 5 --");
                Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                connect.closeConnection();
            }
        }
        return null;
    }


    private List parsRespond(String table) throws SQLException{
        if(table.equals("bay")) {
            return parsRespondPet();
        }
        else {
            System.out.println("-- Ошибка: 4 --");
        }
        return null;
    }


    // Парс ответа из базы в мапу
    private List parsRespondPet() throws SQLException {
        while (result.next()) {
            Map<String, String> mapPetRow = new HashMap <>();
            mapPetRow.put("NAME", result.getString("NAME"));
            mapPetRow.put("AMOUNT",result.getString("AMOUNT"));
            listElements.add(mapPetRow);
        }
        return listElements;
    }




/*    // обработк INSERT / UPDATE запроса
    public void sqlRequestSQL(String sql, Connection connection) {
        this.connection = connection;
        if (connection != null) {
            try {
                statement = connection.createStatement();
                // Запрос
                statement.executeUpdate(sql);
                System.out.println("-- Выполнен SQL: " + sql);
            }
            catch (SQLException ex) {
                Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/



}
