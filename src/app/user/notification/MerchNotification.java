package app.user.notification;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class MerchNotification implements NotificationStrategy {
    @Override
    public void addNotification(ObjectNode destination, String notification) {
        destination.put("name", "New Merchandise");
        destination.put("description", notification);
    }
}
