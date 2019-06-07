package com.musidb;

import com.musicdb.model.Artist;
import com.musicdb.model.Datasource;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        System.out.println("Test");
        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtist();
        if(artists==null){
            System.out.println("No artists!");
            return;
        }
        for(Artist artist: artists){
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        datasource.close();
    }
}
