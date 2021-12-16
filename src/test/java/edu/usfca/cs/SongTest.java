package edu.usfca.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.Assert.*;

public class SongTest {
    Song s1,s2;
    Artist a1;

    @BeforeEach
    void setup(){
        s1 = new Song();
        s2 = new Song();
        a1 = new Artist();
    }

    @Test
    void compareTest(){
        s1.setLikes(5);
        s2.setLikes(1);
        assertEquals(1,s1.compareTo(s2));
    }

    @Test
    void toXML(){
        s1.setEntityID("s2abc");
        s1.setName("All mine");
        a1.setEntityID("6cbc");
        a1.setName("charley");
        s1.setArtist(a1);
        s1.setAlbum(new Album("oceanside","5abc",a1));
        System.out.println(s1.toXML());
    }


}