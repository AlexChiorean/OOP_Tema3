package app.user.notification;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface NotificationStrategy {
    void addNotification(final ObjectNode destination, final String notification);
}
