package edu.usfca.cs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Playlist {
    private ArrayList<Song> listOfSongs;

    public Playlist() {
        listOfSongs = new ArrayList<Song>();
    }
    public Playlist(ArrayList<Song> s) {listOfSongs = s;}

    public void addSong(Song s){
        listOfSongs.add(s);
    }

    public boolean deleteSong(Song s){
        if(listOfSongs.contains(s)){
            listOfSongs.remove(s);
            return true;}
        else
            return false;
    }

    public Playlist merge(Playlist p){
        Set<Song> setSongs = new HashSet<>();
        setSongs.addAll(this.listOfSongs);
        setSongs.addAll(p.listOfSongs);
        this.listOfSongs = new ArrayList<>();
        this.listOfSongs.addAll(setSongs);
        return new Playlist(listOfSongs);
    }

    public void sort(){
        listOfSongs = (ArrayList<Song>) listOfSongs.stream().sorted((x, y)->y.compareTo(x)).collect(Collectors.toList());
    }

    public ArrayList<Song> newRandomList(ArrayList<Song> s) {
        for (int i = 0; i < s.size(); i++) {
            if (s.get(i).genre.equals("Jazz")) {
                listOfSongs.add(s.get(i));
            }
        }
        Collections.shuffle(listOfSongs);
        return listOfSongs;
    }

    public void randomShuffle(){
        Collections.shuffle(listOfSongs);
    }

    public void setListOfSongs(ArrayList<Song> listOfSongs) {
        this.listOfSongs = listOfSongs;
    }

    public ArrayList<Song> getListOfSongs() {
        return listOfSongs;
    }

    public void listToXML() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<playlist> \n"+"\t<songs>\n");
        for(int i=0; i<listOfSongs.size();i++){
            sb.append(listOfSongs.get(i).toXML());
        }
        sb.append("\t</songs>"+"\n</playlist>");
        String output = sb.toString();
        FileWriter fw = new FileWriter("playlist.xml");
        fw.write(output);
        fw.flush();
        fw.close();
    }

    public ArrayList<Song> songsByArtist(String artist){
        listOfSongs = FromSQL.songsFromSQL();
        ArrayList<Song> newListOfSongs = new ArrayList<>();
        for(int i=0; i<listOfSongs.size();i++){
            if(listOfSongs.get(i).getArtist().getName().equals(artist))
                newListOfSongs.add(listOfSongs.get(i));
        }
        return newListOfSongs;
    }

    public void addSongByUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the song here: ");
        String name = sc.next();
        addSong(FromMusicBrainz.readingSong(name));
    }

    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSongByUser();
        System.out.println(playlist.getListOfSongs());
    }
}
