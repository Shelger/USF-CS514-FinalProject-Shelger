package edu.usfca.cs;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private ArrayList<Song> songs;

    public Library() {
        songs = new ArrayList<Song>();
    }
    public boolean findSong(Song s) {
        return songs.contains(s);
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }
    public void addSong(Song s) {
        songs.add(s);
    }




    //Delete duplicates
    public void duplicates() {
        Scanner sc = new Scanner(System.in);
        String delete;
        for(int i=0; i< songs.size()-1; i++){
            for(int j=i+1; j<songs.size();j++){
                if(songs.get(j).entityID == songs.get(i).entityID){
                    System.out.println("Delete duplicates?(yes or no)");
                    delete = sc.nextLine();
                    if(delete.equals("yes")){
                        songs.remove(j);
                        i--;
                        j--;
                    }
                }else if(songs.get(j).name.equals(songs.get(i).name)
                        && (songs.get(j).album.equals(songs.get(i).album)
                        || songs.get(j).artist.equals(songs.get(i).artist))){
                    System.out.println("Delete it or not? (yes or no)");
                    delete = sc.nextLine();
                    if(delete.equals("yes")){
                        songs.remove(j);
                        i--;
                        j--;}
                }else if(songs.get(j).album.equals(songs.get(i).album)
                        && songs.get(j).artist.equals(songs.get(i).artist)
                        && songs.get(j).name.toLowerCase().equals(songs.get(i).name.toLowerCase())){
                    System.out.println("Delete it or not? (yes or no)");
                    delete = sc.nextLine();
                    if(delete.equals("yes")){
                        songs.remove(j);
                        i--;
                        j--;}
                }
            }
        }
        System.out.println(songs);
    }


    public void toXML() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<library> \n"+"\t<songs>\n");
        for(int i=0; i<songs.size();i++){
            sb.append(songs.get(i).toXML());
        }
        sb.append("\t</songs>"+"\n</library>");
        String output = sb.toString();
        FileWriter fw = new FileWriter("library.xml");
        fw.write(output);
        fw.flush();
        fw.close();
    }

    //Because the Junit test can read only, so I use main method to check the method which delete duplicates

}
