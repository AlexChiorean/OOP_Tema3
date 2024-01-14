package app.audio.Files;

import java.util.ArrayList;

public class SongBuilder {
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
     * @param addName the name
     */
    public SongBuilder name(final String addName) {
        this.name = addName;
        return this;
    }

    /**
     * adds the duration to the builder
     * @param addDuration the duration
     */
    public SongBuilder duration(final Integer addDuration) {
        this.duration = addDuration;
        return this;
    }

    /**
     * adds the album to the builder
     * @param addAlbum the album
     */
    public SongBuilder album(final String addAlbum) {
        this.album = addAlbum;
        return this;
    }

    /**
     * adds the tags to the builder
     * @param addTags the tags
     */
    public SongBuilder tags(final ArrayList<String> addTags) {
        this.tags = addTags;
        return this;
    }

    /**
     * adds the lyrics to the builder
     * @param addLyrics the lyrics
     */
    public SongBuilder lyrics(final String addLyrics) {
        this.lyrics = addLyrics;
        return this;
    }

    /**
     * adds the genre to the builder
     * @param addGenre the genre
     */
    public SongBuilder genre(final String addGenre) {
        this.genre = addGenre;
        return this;
    }

    /**
     * adds the releaseYear to the builder
     * @param addReleaseYear the release year
     */
    public SongBuilder releaseYear(final Integer addReleaseYear) {
        this.releaseYear = addReleaseYear;
        return this;
    }

    /**
     * adds the artist to the builder
     * @param addArtist the artist
     */
    public SongBuilder artist(final String addArtist) {
        this.artist = addArtist;
        return this;
    }

    /**
     * builds new song
     * @return the new song
     */
    public Song build() {
        return new Song(this.name, this.duration, this.album, this.tags,
                this.lyrics, this.genre, this.releaseYear, this.artist);
    }
}
