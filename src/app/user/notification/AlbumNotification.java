package app.user.notification;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class AlbumNotification implements NotificationStrategy {
    @Override
    public void addNotification(ObjectNode destination, String notification) {
        destination.put("name", "New Album");
        destination.put("description", notification);
    }
}
