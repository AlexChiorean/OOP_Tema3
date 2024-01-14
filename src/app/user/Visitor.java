package app.user;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Visitor {
    ObjectNode visit(User user, int timestamp);
    ObjectNode visit(Artist artist, int timestamp);
    ObjectNode visit(Host host);
}
