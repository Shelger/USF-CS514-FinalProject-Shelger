package edu.usfca.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class FromSQLTest {

    @Test
    void fromSQL(){
        FromSQL.albumsFromSQL();
        FromSQL.artistsFromSQL();
        FromSQL.songsFromSQL();
    }

}