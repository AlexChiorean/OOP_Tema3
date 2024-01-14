package app.user.notification;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class EventNotification implements NotificationStrategy {
    @Override
    public void addNotification(ObjectNode destination, String notification) {
        destination.put("name", "New Event");
        destination.put("description", notification);
    }
}
