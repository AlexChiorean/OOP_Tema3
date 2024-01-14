package app.user.notification;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface NotificationStrategy {
    /**
     * adds notification to the notification list
     * @param destination
     * @param notification
     */
    void addNotification(ObjectNode destination, String notification);
}
