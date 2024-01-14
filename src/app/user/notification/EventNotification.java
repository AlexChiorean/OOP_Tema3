package app.user.notification;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class EventNotification implements NotificationStrategy {
    @Override
    public void addNotification(final ObjectNode destination, final String notification) {
        destination.put("name", "New Event");
        destination.put("description", notification);
    }
}
