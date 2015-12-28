package ru.samvel.jetty.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {

    java.sql.Connection connection = null;

    static String url = "";
    static String name = "";
    static String password = "";

    // Открыть
    public void setConnection() throws SQLException {
        try {
            //Загружаем драйвер
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //System.out.println("-- Ошибка: 1 --");

            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
        }
        catch (Exception ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("-- Ошибка: 2 --");
        }
    }

    public java.sql.Connection getConnection(){
        return connection;
    }

    // Закрыть соединение к базе
    public void closeConnection(){
        if (connection != null) {
            try {
                connection.close();
                System.out.println("-- Ошибка: 3 --");
            }
            catch (SQLException ex) {
                Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }




}
