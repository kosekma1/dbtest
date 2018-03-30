package com.martink;

import java.sql.*;

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Programy\\sqlitedb\\" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {

        /*
        // automatically close connection after try catch block exited
        try(Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
        }
        */

        try {
            //Class.forName("org.sql.JDBC"); used to use
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
//          conn.setAutoCommit(false); //default behavior is autocommit true
            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + " TEXT, "
                        + COLUMN_PHONE + " INTEGER, "
                        + COLUMN_EMAIL + " TEXT " +
                    ")");

            insertContact(statement, "Tim", 6545678, "tim@gmail.com");
            insertContact(statement, "Joe", 852652553, "joe@gmail.com");
            insertContact(statement, "Jane", 4654654, "jane@gmail.com");
            insertContact(statement, "Fido", 321, "fido@gmail.com");

            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " +
                    COLUMN_PHONE + "=1234 " +
                    "WHERE " + COLUMN_NAME + "='Jane'");

            statement.execute("DELETE FROM " + TABLE_CONTACTS +
                    " WHERE " + COLUMN_NAME + "='Joe'");

            /* Longer version of following expression
            statement.execute("SELECT * FROM contacts");
            ResultSet results = statement.getResultSet();
            */

            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS); //shorter than previous expressions

            while(results.next()){ //we use column names to get values
                System.out.println(results.getString(COLUMN_NAME) + " " +
                                                      results.getInt(COLUMN_PHONE) + " " +
                                                              results.getString(COLUMN_EMAIL)
                );
            }

            results.close();

            //right order - statement first then conn to close
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                "(" + COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL +
                ") " +
                "VALUES ('"+ name + "'," + phone + ", ' " + phone + "')");

    }

}
