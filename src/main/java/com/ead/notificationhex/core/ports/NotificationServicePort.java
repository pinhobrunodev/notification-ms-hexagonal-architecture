package com.ead.notificationhex.core.ports;


import com.ead.notificationhex.core.domain.NotificationDomain;
import com.ead.notificationhex.core.domain.PageInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
// Porta que vai comunicar com os Adapters : Controller e Consumer
public interface NotificationServicePort {
    NotificationDomain saveNotification(NotificationDomain notificationDomain);
    List<NotificationDomain> findAllNotificationByUser(UUID userId, PageInfo pageable);
    Optional<NotificationDomain> findByNotificationIdAndUserId(UUID notificationId, UUID userId);
}
