package edu.usfca.cs;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class PlaylistTest {
    Playlist p1,p2,p3;
    Song s1,s2,s3;

    @BeforeEach
    void setup(){
        p1 = new Playlist();
        p2 = new Playlist();
        p3 = new Playlist();
        s1 = new Song("S1");
        s2 = new Song("S2");
        s3 = new Song("S3");
    }

    @Test
    void sortTest(){
        s1.setLikes(3);
        s2.setLikes(2);
        s3.setLikes(4);
        p1.addSong(s1);
        p1.addSong(s3);
        p2.addSong(s2);
        p3 = p1.merge(p2);
        System.out.println(p3.getListOfSongs());
        p3.sort();
        System.out.println(p3.getListOfSongs());
        p3.randomShuffle();
        System.out.println(p3.getListOfSongs());
    }

    @Test
    void random(){
        s1.setGenre("Country");
        s2.setGenre("Jazz");
        s3.setGenre("Jazz");
        p2.addSong(s1);
        p2.addSong(s2);
        p2.addSong(s3);
        System.out.println(p1.newRandomList(p2.getListOfSongs()));
    }

    @Test
    void toXMLTest() throws IOException {
        p1.setListOfSongs(p1.songsByArtist("Rapalje"));
        p1.listToXML();
    }


}