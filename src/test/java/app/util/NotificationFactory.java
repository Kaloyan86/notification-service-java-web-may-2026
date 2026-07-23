package app.util;

import app.model.NotificationStatus;
import app.model.NotificationType;
import app.web.dto.NotificationResponse;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class NotificationFactory {

    public static NotificationResponse getNotificationResponse() {
        return NotificationResponse.builder()
                .subject("Some Subject")
                .type(NotificationType.EMAIL)
                .status(NotificationStatus.SUCCEEDED)
                .createdOn(LocalDateTime.now())
                .build();

    }
}
