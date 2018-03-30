package com.musidb;

import com.musicdb.model.Datasource;

public class MainApp {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        datasource.close();
    }
}
