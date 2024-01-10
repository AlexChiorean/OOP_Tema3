package app.user;

import app.audio.Collections.Podcast;
import app.pages.HostPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static app.utils.MapManagement.addMapToNode;
import static app.utils.MapManagement.getTopFive;

/**
 * The type Host.
 */
public final class Host extends ContentCreator {
    private ArrayList<Podcast> podcasts;
    private ArrayList<Announcement> announcements;

    @Getter @Setter
    private Map<String, Integer> topEpisodes;
    @Getter @Setter
    private int listeners;

    /**
     * Instantiates a new Host.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public Host(final String username, final int age, final String city) {
        super(username, age, city);
        podcasts = new ArrayList<>();
        announcements = new ArrayList<>();

        super.setPage(new HostPage(this));

        topEpisodes = new HashMap<>();
        listeners = 0;
    }

    /**
     * Gets podcasts.
     *
     * @return the podcasts
     */
    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    /**
     * Sets podcasts.
     *
     * @param podcasts the podcasts
     */
    public void setPodcasts(final ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    /**
     * Gets announcements.
     *
     * @return the announcements
     */
    public ArrayList<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     * Sets announcements.
     *
     * @param announcements the announcements
     */
    public void setAnnouncements(final ArrayList<Announcement> announcements) {
        this.announcements = announcements;
    }

    /**
     * Gets podcast.
     *
     * @param podcastName the podcast name
     * @return the podcast
     */
    public Podcast getPodcast(final String podcastName) {
        for (Podcast podcast: podcasts) {
            if (podcast.getName().equals(podcastName)) {
                return podcast;
            }
        }

        return null;
    }

    /**
     * Gets announcement.
     *
     * @param announcementName the announcement name
     * @return the announcement
     */
    public Announcement getAnnouncement(final String announcementName) {
        for (Announcement announcement: announcements) {
            if (announcement.getName().equals(announcementName)) {
                return announcement;
            }
        }

        return null;
    }

    @Override
    public String userType() {
        return "host";
    }

    /**
     * Gets wrapped stats.
     *
     * @return the wrapped stats
     */
    public ObjectNode wrapped() {
        Map<String, Integer> sortedTopEpisodes = getTopFive(topEpisodes);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode output = objectMapper.createObjectNode();

        addMapToNode(output, "topEpisodes", sortedTopEpisodes);

        output.put("listeners", listeners);
        return output;
    }
}
