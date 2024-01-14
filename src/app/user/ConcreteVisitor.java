package app.user;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class ConcreteVisitor implements Visitor {
    @Override
    public ObjectNode visit(final User user, final int timestamp) {
        return user.wrapped(timestamp);
    }

    @Override
    public ObjectNode visit(final Artist artist, final int timestamp) {
        return artist.wrapped(timestamp);
    }

    @Override
    public ObjectNode visit(final Host host) {
        return host.wrapped();
    }
}
