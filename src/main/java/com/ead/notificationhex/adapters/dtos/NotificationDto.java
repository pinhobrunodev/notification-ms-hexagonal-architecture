package com.ead.notificationhex.adapters.dtos;

import com.ead.notificationhex.core.domain.enums.NotificationStatus;

import javax.validation.constraints.NotNull;

public class NotificationDto {

    @NotNull
    private NotificationStatus notificationStatus;

    public NotificationDto() {
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public NotificationDto(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }
}
