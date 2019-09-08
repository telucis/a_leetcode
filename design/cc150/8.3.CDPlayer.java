public class CD {}
public class CDPlayer {
    private PlayList p;
    private CD c;

    public Playlist getPlaylist() { return p; }
    public void setPlaylist(Playlist p) { this.p = p; }
    public CD getCD() { return c; }
    public void setCD(CD c) { this.c = c; }

    public CDPlayer(Playlist p) { this.p = p; }
    public CDPlayer(CD c){ this.c = c; }
    public CDPlayer(CD c, Playlist p) { 
        this.p = p;
        this.c = c;
    }
    
    public void playSong(Song s) {  }
}
public class JukeBox {
    private CDPlayer cdPlayer;
    private User user;
    private Set<CD> cdCollection;
    private SongSelector ts;
    
    public JukeBox(CDPlayer cdPlayer, User user, Set<CD> cdCollection,
                   SongSelector ts) {
        super();
        this.cdPlayer = cdPlayer;
        this.user = user;
        this.cdCollection = cdCollection;
        this.ts = ts;
    }
    
    public Song getCurrentSong() { return ts.getCurrentSong();  }
    public void setUser(User u) { this.user = u;    }
}
public class PlayList {
    private Song song;
    private Queue<Song> queue;
    public PlayList(Song song, Queue<Song> queue) {
        this.song = song;
        this.queue = queue;
    }
    public Song getNextSontToPlay() {return queue.peek();}
    public void queueUpSong(Song s) {queue.add(s);}
}
public class Song {
    private String songName;
    public String toString() { return songName; }
}
public class SongSelector {
    private Song currentSong;
    public SongSelector(Song s) { currentSong=s; }
    public void setSong(Song s) { currentSong = s;  }
    public Song getCurrentSong() { return currentSong;  }
}
public class User {

}