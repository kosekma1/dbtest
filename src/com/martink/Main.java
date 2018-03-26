package com.martink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        // automatically close connection after try catch block exited
//        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Programy\\sqlitedb\\testjava.db")){
//            Statement statement = conn.createStatement();
//            statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");

        try {
            //Class.forName("org.sql.JDBC"); used to use
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Programy\\sqlitedb\\testjava.db");
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS contacts (name TEXT, phone INTEGER, email TEXT)");
            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Joe', 852652553, 'joe@gmail.com')");

            //right order - statement first then conn to close
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }

    }
}
