package edu.usfca.cs;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.net.URLConnection;

public class FromMusicBrainz {

    /**
     *
     * @param theSong It is the name of the song users want to add into the database.
     * @return return the object Song
     * It is similar to return Album and Artist, so I only do the most complex one, the song part.
     */
    public static Song readingSong(String theSong) {
        theSong = theSong.replaceAll("\\s+", "%20");
        String initialURL = "https://musicbrainz.org/ws/2/recording?query=" + theSong + "&fmt=xml";
        /* MusicBrainz gives each element in their DB a unique ID, called an MBID. We'll use this to fecth that. */

        /* now let's parse the XML.  */
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            URLConnection u = new URL(initialURL).openConnection();
            /* MusicBrainz asks to have a user agent string set. This way they can contact you if there's an
             * issue, and they won't block your IP. */
            u.setRequestProperty("User-Agent", "Final project (yzhang433@usfca.edu)");

            Document doc = db.parse(u.getInputStream());
            /* let's get the artist-list node */
            NodeList songsMB = doc.getElementsByTagName("recording-list");
            /* let's assume that the one we want is first. */
            Node songNode = songsMB.item(0).getFirstChild();
            Node songIDNode = songNode.getAttributes().getNamedItem("id");
            String song_name = songNode.getFirstChild().getTextContent();
            String id = songIDNode.getNodeValue();

            /* Now let's use that ID to get info specifically about this artist. */

            String lookupURL = "https://musicbrainz.org/ws/2/recording/" + id + "?inc=aliases%2Bartist-credits%2Breleases";
            URLConnection u2 = new URL(lookupURL).openConnection();

            u2.setRequestProperty("User-Agent", "Final project (yzhang433@usfca.edu)");

            db = dbf.newDocumentBuilder();
            doc = db.parse(u2.getInputStream());
            /* let's get all the aliases. */
            NodeList artist = doc.getElementsByTagName("artist");
            NodeList album = doc.getElementsByTagName("release");

            /* Get artist and album here. */
            String artist_id = artist.item(0).getAttributes().getNamedItem("id").getTextContent();
            String artist_name = artist.item(0).getFirstChild().getTextContent();
            Artist artist1 = new Artist(artist_name, artist_id);
            artist1.toSQL();
            String album_id = album.item(0).getAttributes().getNamedItem("id").getTextContent();
            String album_name = album.item(0).getFirstChild().getTextContent();
            Album album1 = new Album(album_name, album_id, artist1);
            album1.toSQL();

            Song newSong = new Song(song_name, id, album1, artist1);
            newSong.toSQL();
            return newSong;
        } catch (Exception ex) {
            System.out.println("XML parsing error" + ex);
            return null;
        }
    }
}
