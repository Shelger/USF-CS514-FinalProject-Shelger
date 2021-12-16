package edu.usfca.cs;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FromSQL {

    public static ArrayList<Album> albumsFromSQL() {
        Connection connection = null;
        ArrayList<Album> albums = new ArrayList<>();
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select albums.id,albums.name,artists.id as id2,artists.name as name2 from albums,artists where albums.artist_id = artists.id;");
            while (rs.next()) {
                // read the result set
                Album album = new Album(rs.getString("name"), rs.getString("id"),new Artist(rs.getString("name2"),rs.getString("id2")));
                albums.add(album);
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        for (Album album : albums) {
            System.out.println(album);
        }
        return albums;
    }

    public static ArrayList<Artist> artistsFromSQL() {
        Connection connection = null;
        ArrayList<Artist> artists = new ArrayList<>();
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from artists");
            while (rs.next()) {
                // read the result set
                Artist artist = new Artist(rs.getString("name"), rs.getString("id"));
                artists.add(artist);
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        for(Artist artist:artists){
            System.out.println(artist);
        }
        return artists;
    }

    public static ArrayList<Song> songsFromSQL() {
        Connection connection = null;
        ArrayList<Song> songs = new ArrayList<>();
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select songs.id as id1, songs.name as name1, albums.id as id2, albums.name as name2,artists.id as id3, artists.name as name3 from songs,albums,artists where songs.artist_id = id3 and songs.album_id = id2; ");
            while (rs.next()) {
                // read the result set
                Song song = new Song(rs.getString("name1"), rs.getString("id1"),
                        new Album(rs.getString("name2"),rs.getString("id2"),new Artist(rs.getString("name3"),rs.getString("id3"))),
                                new Artist(rs.getString("name3"),rs.getString("id3")));
                songs.add(song);
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return songs;
    }



}