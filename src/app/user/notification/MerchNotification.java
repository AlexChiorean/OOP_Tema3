package app.user.notification;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class MerchNotification implements NotificationStrategy {
    @Override
    public void addNotification(final ObjectNode destination, final String notification) {
        destination.put("name", "New Merchandise");
        destination.put("description", notification);
    }
}
