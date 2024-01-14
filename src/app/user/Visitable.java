package app.user;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Visitable {
    /**
     * Accepts the visitor.
     */
    ObjectNode accept(Visitor visitor, int timestamp);
}
