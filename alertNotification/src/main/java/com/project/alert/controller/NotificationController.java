package com.project.alert.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.alert.model.NotificationRequest;
import com.project.alert.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@PostMapping("/send")
	public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
		// Validate request
		
		System.out.println(request.getUserId());
		if (request == null || request.getUserId() == null || request.getMassage() == null) {
			return ResponseEntity.badRequest().body("Invalid request");
		}
		boolean success = notificationService.sendEmailNotification(request.getUserId(), request.getMassage	(),request.getMail());

		if (success) {
			return ResponseEntity.ok("Notification sent successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send notification");
		}
	}
}
