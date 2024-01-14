package app.user;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import app.audio.Collections.Album;
import app.audio.Collections.AlbumOutput;
import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.pages.ArtistPage;
import app.player.Player;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;

import static app.utils.MapManagement.updatePlaylistStats;
import static app.utils.MapManagement.updateAlbumStats;
import static app.utils.MapManagement.getTopFive;
import static app.utils.MapManagement.addMapToNode;


/**
 * The type Artist.
 */
public final class Artist extends ContentCreator {
    private ArrayList<Album> albums;
    private ArrayList<Merchandise> merch;
    private ArrayList<Event> events;

    @Getter @Setter
    private Map<String, Integer> topAlbums;
    @Getter @Setter
    private Map<String, Integer> topSongs;
    @Getter @Setter
    private Map<String, Integer> topFans;
    @Getter @Setter
    private int listeners;

    @Getter @Setter
    private double merchRevenue;
    @Getter @Setter
    private double songRevenue;
    @Getter @Setter
    private int sales;
    @Getter @Setter
    private String mostProfitableSong;

    @Override
    public ObjectNode accept(final Visitor visitor, final int timestamp) {
        return visitor.visit(this, timestamp);
    }

    /**
     * Instantiates a new Artist.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public Artist(final String username, final int age, final String city) {
        super(username, age, city);
        albums = new ArrayList<>();
        merch = new ArrayList<>();
        events = new ArrayList<>();

        super.setPage(new ArtistPage(this));

        topAlbums = new HashMap<>();
        topSongs = new HashMap<>();
        topFans = new HashMap<>();
        listeners = 0;

        merchRevenue = 0;
        songRevenue = 0;
        sales = 0;
        mostProfitableSong = "N/A";
    }

    /**
     * Gets albums.
     *
     * @return the albums
     */
    public ArrayList<Album> getAlbums() {
        return albums;
    }

    /**
     * Gets merch.
     *
     * @return the merch
     */
    public ArrayList<Merchandise> getMerch() {
        return merch;
    }

    /**
     * Gets events.
     *
     * @return the events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Gets event.
     *
     * @param eventName the event name
     * @return the event
     */
    public Event getEvent(final String eventName) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }

        return null;
    }

    /**
     * Gets album.
     *
     * @param albumName the album name
     * @return the album
     */
    public Album getAlbum(final String albumName) {
        for (Album album : albums) {
            if (album.getName().equals(albumName)) {
                return album;
            }
        }

        return null;
    }

    /**
     * Gets all songs.
     *
     * @return the all songs
     */
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        albums.forEach(album -> songs.addAll(album.getSongs()));

        return songs;
    }

    /**
     * Show albums array list.
     *
     * @return the array list
     */
    public ArrayList<AlbumOutput> showAlbums() {
        ArrayList<AlbumOutput> albumOutput = new ArrayList<>();
        for (Album album : albums) {
            albumOutput.add(new AlbumOutput(album));
        }

        return albumOutput;
    }

    /**
     * Get user type
     *
     * @return user type string
     */
    public String userType() {
        return "artist";
    }

    /**
     * Gets wrapped stats.
     *
     * @return the wrapped stats
     */
    public ObjectNode wrapped(final int timestamp) {
        for (User user : app.Admin.getInstance().getUsers()) {
            Player player = user.getPlayer();
            if (player.getLastLoadedSource() != null
                    && player.getType().equals("playlist")) {

                Playlist playlist = (Playlist) player.getCurrentAudioCollection();
                updatePlaylistStats(user, player, playlist, timestamp);
                player.setLastTimestamp(timestamp);

            } else if (player.getLastLoadedSource() != null
                    && player.getType().equals("album")) {

                Album album = (Album) player.getCurrentAudioCollection();
                updateAlbumStats(user, player, album, timestamp);
                player.setLastTimestamp(timestamp);
            }
        }

        Map<String, Integer> sortedTopAlbums = getTopFive(topAlbums);
        Map<String, Integer> sortedTopSongs = getTopFive(topSongs);
        Map<String, Integer> sortedTopFans = getTopFive(topFans);
        Set<String> keySet = sortedTopFans.keySet();
        List<String> topFansNames = new ArrayList<>(keySet);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode output = objectMapper.createObjectNode();

        addMapToNode(output, "topAlbums", sortedTopAlbums);
        addMapToNode(output, "topSongs", sortedTopSongs);
        output.put("topFans", objectMapper.valueToTree(topFansNames));

        output.put("listeners", topFans.size());
        return output;
    }
}
