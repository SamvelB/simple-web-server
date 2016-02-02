package ru.samvel.jetty.db;


import java.util.ArrayList;
import java.util.List;

public class ControllerDB {


    private Queries query = new Queries();
    //private Map<Integer, Map<String,String>> mapPetList = new HashMap<>();
    List listElements = new ArrayList();


    // SELECT * FROM table
    public List selectTable(String table){
        String sql = "SELECT * FROM " + table;
        listElements = query.sqlRequestSQL(sql, table);
        return listElements;
    }







/*    public Map<Integer, Map<String, String>> select(String table, String sql){
        mapPet = doSelect(sql, table);
        return mapPet;
    }

    // insert into table new pet
    public void insert(String table, String values){
        String sql = "INSERT INTO " + table + " VALUES " + "(" + values + ")";
        doInsert(sql);
        // INSERT INTO pet VALUES ('', 'type_6', 'Elis', 'green')
    }
    // insert into table new pet
    public void create(String table, String values){
        String sql = "INSERT INTO " + table + "(type, name, color)" + " VALUES " + "(" + values + ")";
        doInsert(sql);
        // INSERT INTO pet (type, name, color) VALUES ('type_6', 'Elis', 'green')
    }
    // insert into table new pet
    public void insert(String sql){
        doInsert(sql);
    }



    // insert into table
    public void insert(String table, String columns, String values) {
        String sql = "INSERT INTO " + table + " (" + columns + ") " + " VALUES " + values;
        // INSERT INTO pet_life (PET_ID, BIRTHDAY, ENERGY, EAT, PLAY) VALUES ('', 'type_6', 'Elis', 'green')
    }

    // insert into table
    public void insert(String table, String columns, String values, String condition) {
        String sql = "INSERT INTO " + table + " (" + columns + ") " + " VALUES " + values + " WHERE " + condition;
        // INSERT INTO pet (PET_ID, TYPE, NAME, COLOR) VALUES ('', 'type_6', 'Elis', 'green') WHERE pet_id = 123
    }



    // SELECT
    private Map<Integer, Map<String, String>> doSelect(String sql, String table){
        try {
            //Установка соединения
            connect.setConnection();
            connection = connect.getConnection();

            // Получение мапы
            mapPet = query.sqlRequestSQL(sql, table, connection);
        }
        catch (Exception ex){
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            connect.closeConnection();
        }
        return mapPet;
    }


    // INSERT / UPDATE
    private void doInsert(String sql){
        try {
            //Установка соединения
            connect.setConnection();
            connection = connect.getConnection();
            query.sqlRequestSQL(sql, connection);
        }
        catch (Exception ex){
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            connect.closeConnection();
        }
    }*/



}
