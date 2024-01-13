package app.utils;

import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.player.Player;
import app.user.Artist;
import app.user.Host;
import app.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;


public class MapManagement {
    /**
     * Sorts map by value, then by key.
     *
     * @param map the unsorted map
     * @return the sorted map
     */
    public static Map<String, Double> sortByKeyThenValueDouble(final Map<String, Double> map) {
        List<Map.Entry<String, Double>> entryList = new ArrayList<>(map.entrySet());

        Comparator<Map.Entry<String, Double>> byValueAndKey = Comparator
                .<Map.Entry<String, Double>>comparingDouble(Map.Entry::getValue).reversed()
                .thenComparing(Map.Entry::getKey);

        entryList.sort(byValueAndKey);

        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    /**
     * Sorts map by value, then by key.
     *
     * @param map the unsorted map
     * @return the sorted map
     */
    public static Map<String, Integer> sortByKeyThenValue(final Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());

        Comparator<Map.Entry<String, Integer>> byValueAndKey = Comparator
                .<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue).reversed()
                .thenComparing(Map.Entry::getKey);

        entryList.sort(byValueAndKey);

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    /**
     * Returns the first 5 entries of the sorted map.
     *
     * @param hashMap
     * @return sorted top 5
     */
    public static Map<String, Integer> getTopFive(final Map<String, Integer> hashMap) {

        Map<String, Integer> entryList = sortByKeyThenValue(hashMap);
        int count = 0;
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList.entrySet()) {
            count++;
            sortedMap.put(entry.getKey(), entry.getValue());
            if (count == 5) return sortedMap;
        }
        return sortedMap;
    }

    /**
     * Adds the contents of the map to a node.
     */
    public static void addMapToNode(final ObjectNode outputs,
                                    final String field,
                                    final Map<String, Integer> map) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode nodeList = objectMapper.createObjectNode();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            nodeList.put(entry.getKey(), entry.getValue());
        }
        outputs.put(field, nodeList);
    }

    /**
     * Updates the hash maps with the new stats for
     * both the user and the song's artist.
     */
    public static void updateSongStats(final User user,
                                       final Song song) {

        user.getTopSongs().merge(song.getName(), 1, Integer::sum);
        user.getTopGenres().merge(song.getGenre(), 1, Integer::sum);
        user.getTopAlbums().merge(song.getAlbum(), 1, Integer::sum);
        user.getTopArtists().merge(song.getArtist(), 1, Integer::sum);

        if (user.getPlayer().getCurrentSong() != null) {
            Song currentSong = user.getPlayer().getCurrentSong();
            user.getTopSongs().merge(currentSong.getName(), -1, Integer::sum);
            user.getTopGenres().merge(currentSong.getGenre(), -1, Integer::sum);
            user.getTopAlbums().merge(currentSong.getAlbum(), -1, Integer::sum);
            user.getTopArtists().merge(currentSong.getArtist(), -1, Integer::sum);
        }

        for (Artist artist : app.Admin.getInstance().getArtists()) {
            if (artist.getUsername().equals(song.getArtist())) {
                artist.getTopFans().merge(user.getUsername(), 1, Integer::sum);
                artist.getTopSongs().merge(song.getName(), 1, Integer::sum);
                artist.getTopAlbums().merge(song.getAlbum(), 1, Integer::sum);

                if (user.getPlayer().getCurrentSong() != null) {
                    Song currentSong = user.getPlayer().getCurrentSong();
                    artist.getTopFans().merge(user.getUsername(), -1, Integer::sum);
                    artist.getTopSongs().merge(currentSong.getName(), -1, Integer::sum);
                    artist.getTopAlbums().merge(currentSong.getAlbum(), -1, Integer::sum);
                }
            }
        }
    }

    /**
     * Updates the hash maps with the new stats for
     * both the user and the songs' artists.
     */
    public static void updatePlaylistStats(final User user,
                                           final Player player,
                                           final Playlist playlist,
                                           final int timestamp) {

        int timeInterval = timestamp - player.getLastTimestamp();
        if (playlist != null) {
            for (Song song : playlist.getSongs()) {
                if (timeInterval >= 0) {
                    timeInterval -= song.getDuration();
                    updateSongStats(user, song);
                }
            }
        }
    }

    /**
     * Updates the hash maps with the new stats for
     * both the user and the songs' artist.
     */
    public static void updateAlbumStats(final User user,
                                        final Player player,
                                        final Album album,
                                        final int timestamp) {

        int timeInterval = timestamp - player.getLastTimestamp();
        boolean gotToCurrentSong = false;

        if (album != null) {
            for (Song song : album.getSongs()) {

                if (player.getCurrentSong() != null
                        && song == player.getCurrentSong()) {
                    gotToCurrentSong = true;
                }
                if ((player.getCurrentSong() == null || gotToCurrentSong)
                        && timeInterval >= 0) {
                    timeInterval -= song.getDuration();
                    updateSongStats(user, song);
                    if (timeInterval < 0) {
                        player.setCurrentSong(song);
                    } else {
                        player.setCurrentSong(null);
                    }
                }
            }
        }
    }

    /**
     * Updates the hash maps with the new stats for
     * both the user and the episode's owner.
     */
    public static void updateEpisodeStats(final User user,
                                          final Episode episode,
                                          final Podcast podcast) {

        user.getTopEpisodes().merge(episode.getName(), 1, Integer::sum);

        for (Host host : app.Admin.getInstance().getHosts()) {
            if (host.getUsername().equals(podcast.getOwner())) {
                host.getTopEpisodes().merge(episode.getName(), 1, Integer::sum);
                host.getTopFans().merge(user.getUsername(), 1, Integer::sum);
            }
        }
    }

    /**
     * Updates the hash maps with the new stats for
     * both the user and the episodes' owner.
     */
    public static void updatePodcastStats(final User user,
                                          final Player player,
                                          final Podcast podcast,
                                          final int timestamp) {

        int timeInterval = timestamp - player.getLastTimestamp();
        if (podcast != null) {
            for (Episode episode : podcast.getEpisodes()) {
                if (timeInterval >= 0) {
                    timeInterval -= episode.getDuration();
                    updateEpisodeStats(user, episode, podcast);
                }
            }
        }
    }
}
