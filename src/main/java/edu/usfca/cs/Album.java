package edu.usfca.cs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Album extends Entity {
    protected ArrayList<Song> songs;
    protected Artist artist;

    public Album(){}
    public Album(String album_name, String album_id, Artist artist) {
        super(album_name,album_id);
        this.artist = artist;
    }

    public void toSQL(){
        String output = "('" + this.entityID + "','" + this.name + "','" + this.artist.getEntityID()+"')";
        System.out.println(output);
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("insert into albums values" + output);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
