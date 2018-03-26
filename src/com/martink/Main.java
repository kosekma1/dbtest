package com.martink;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        // automatically close connection after try catch block exited
//        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Programy\\sqlitedb\\testjava.db")){
//            Statement statement = conn.createStatement();
//            statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");

        try {
            //Class.forName("org.sql.JDBC"); used to use
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Programy\\sqlitedb\\testjava.db");
//            conn.setAutoCommit(false); //default behavior is autocommit true
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS contacts (name TEXT, phone INTEGER, email TEXT)");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Joe', 852652553, 'joe@gmail.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Jane', 4654654, 'jane@gmail.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Fido', 321, 'fido@gmail.com')");
//            statement.execute("UPDATE contacts SET phone=1234 WHERE name='Jane'");
//            statement.execute("DELETE FROM contacts WHERE name='Joe'");

            statement.execute("SELECT * FROM contacts");
            ResultSet results = statement.getResultSet();
            while(results.next()){
                System.out.println(results.getString("name") + " " +
                                                      results.getInt("phone") + " " +
                                                              results.getString("email")
                );
            }

            results.close();

            //right order - statement first then conn to close
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }

    }
}
