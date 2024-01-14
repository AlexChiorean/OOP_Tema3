package app.audio.Files;

import java.util.ArrayList;

public class SongBuilder{
    private String name;
    private Integer duration;
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private Integer releaseYear;
    private String artist;

    /**
     * adds the name to the builder
     * @param name the name
     */
    public SongBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * adds the duration to the builder
     * @param duration the duration
     */
    public SongBuilder duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    /**
     * adds the album to the builder
     * @param album the album
     */
    public SongBuilder album(String album) {
        this.album = album;
        return this;
    }

    /**
     * adds the tags to the builder
     * @param tags the tags
     */
    public SongBuilder tags(ArrayList<String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * adds the lyrics to the builder
     * @param lyrics the lyrics
     */
    public SongBuilder lyrics(String lyrics) {
        this.lyrics = lyrics;
        return this;
    }

    /**
     * adds the genre to the builder
     * @param genre the genre
     */
    public SongBuilder genre(String genre) {
        this.genre = genre;
        return this;
    }

    /**
     * adds the releaseYear to the builder
     * @param releaseYear the release year
     */
    public SongBuilder releaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    /**
     * adds the artist to the builder
     * @param artist the artist
     */
    public SongBuilder artist(String artist) {
        this.artist = artist;
        return this;
    }

    /**
     * builds new song
     * @return the new song
     */
    public Song build() {
        return new Song(name, duration, album, tags, lyrics, genre, releaseYear, artist);
    }
}
