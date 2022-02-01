package com.ead.notificationhex.adapters.inbound.controllers;

import com.ead.notificationhex.adapters.dtos.NotificationDto;
import com.ead.notificationhex.core.domain.NotificationDomain;
import com.ead.notificationhex.core.domain.PageInfo;
import com.ead.notificationhex.core.ports.NotificationServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserNotificationController {


    final NotificationServicePort notificationServicePort;
    public UserNotificationController(NotificationServicePort notificationServicePort) {
        this.notificationServicePort = notificationServicePort;
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping(value = "/users/{userId}/notifications")
    public ResponseEntity<Page<NotificationDomain>> getAllNotificationsByUser(@PathVariable UUID userId,
                                                                              @PageableDefault(page = 0, size = 10, sort = "notificationId", direction = Sort.Direction.ASC)
                                                                                     Pageable pageable,
                                                                              Authentication authentication) {
        var pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable,pageInfo);
        List<NotificationDomain> notificationDomainList = notificationServicePort.findAllNotificationByUser(userId,pageInfo);
        return ResponseEntity.ok().body(new PageImpl<NotificationDomain>(notificationDomainList,pageable,notificationDomainList.size()));
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @PutMapping(value = "/users/{userId}/notifications/{notificationId}")
    public ResponseEntity<Object> updateNotification(@PathVariable UUID userId,
                                                     @PathVariable UUID notificationId,
                                                     @RequestBody @Valid NotificationDto notificationDto) {
        Optional<NotificationDomain> notificationModelOptional = notificationServicePort.findByNotificationIdAndUserId(notificationId, userId);
        if (notificationModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found!");
        }
        notificationModelOptional.get().setNotificationStatus(notificationDto.getNotificationStatus());
        notificationServicePort.saveNotification(notificationModelOptional.get());
        return ResponseEntity.ok().body(notificationModelOptional.get());
    }
}
