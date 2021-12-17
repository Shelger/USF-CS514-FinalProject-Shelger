package edu.usfca.cs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Artist extends Entity {


    public Artist(){
    }
    public Artist(String name,String id){
        super(name,id);
    }

    public void toSQL() {
        String output = "('" + this.entityID + "','" + this.name + "'" + ")";
        System.out.println(output);
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("insert into artists values" + output);
        } catch (
                SQLException e) {
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

    public Artist getArtist(String id){
        return new Artist();
    }




}
