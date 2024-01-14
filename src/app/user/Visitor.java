package app.user;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Visitor {
    /**
     * Visits user for wrapped.
     */
    ObjectNode visit(User user, int timestamp);

    /**
     * Visits artist for wrapped.
     */
    ObjectNode visit(Artist artist, int timestamp);

    /**
     * Visits host for wrapped.
     */
    ObjectNode visit(Host host);
}
