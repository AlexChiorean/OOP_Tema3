package app.user;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Visitable {
    ObjectNode accept(Visitor visitor, int timestamp);
}
