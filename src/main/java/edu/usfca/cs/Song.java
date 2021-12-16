package edu.usfca.cs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Song extends Entity {
    protected Artist artist;
    protected Album album;
    protected String genre;
    protected int likes;

    public Song(){};
    public Song(String s){
        name = s;
    }
    public Song(String name, String id, Album album, Artist artist){
        this.name = name;
        this.entityID = id;
        this.album = album;
        this.artist = artist;
    }


    public int compareTo(Song s){
        if(this.likes>s.likes) return 1;
        else if(this.likes<s.likes) return -1;
        else return 0;
    }

    public String toXML(){
        return "\t\t<song id=\""+entityID+"\">\n\t\t\t"
                +"<title>"+this.name+"\t</title>\n\t\t\t"
                +"<artist id=\""+this.artist.entityID+"\">"+this.artist.getName()+"\t</artist>\n\t\t\t"
                +"<album id=\""+this.album.entityID+"\">"+this.album.getName()+"\t</album>\n"
                +"\t\t</song>\n";
    }

    public void toSQL() {
        String output = "('" + this.entityID + "','" + this.name + "','" + this.album.getEntityID() + "','" + this.artist.getEntityID() + "')";
        System.out.println(output);
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("insert into songs values" + output);
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




    public String toString(){
        return "Name: "+name+" ID: "+entityID+ "\nAlbum: "+album + "Artist: "+artist;
    }



    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }


}
