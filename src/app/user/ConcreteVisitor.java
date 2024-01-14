package app.user;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ConcreteVisitor implements Visitor{
    @Override
    public ObjectNode visit(User user, int timestamp) {
        return user.wrapped(timestamp);
    }

    @Override
    public ObjectNode visit(Artist artist, int timestamp) {
        return artist.wrapped(timestamp);
    }

    @Override
    public ObjectNode visit(Host host) {
        return host.wrapped();
    }
}
