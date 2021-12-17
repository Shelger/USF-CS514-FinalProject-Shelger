1. The class Song,Album,Artist,Playlist,Library are copied from previous projects. Tests as well.
2. FromSQL is the class which can extract the arraylist of songs,albums or artists. (All of them are static methods)
3. FromMusicBrainz class is used to get missing information from MusicBrainz, and the method inside return a SONG searched by its name. (readingSong(String name))
4. I also added a method addSongByUser to create a playlist by users, the Scanner is inside the main method in the class Playlist, you can run it there.
5. The GUI I chose is Java Swing, it can be run by the main method in the class GUI.
The table shows the library, and you can add new songs by press the button "add", then enter the name of the song, and press ok.
The song will be added automatically.
6. Notice: The XML files in MusicBrainz are not stable, so the error happens sometimes. Try more times will get the output successfully.
7. Test files include all files in the test folder and main methods in Playlist and GUI.