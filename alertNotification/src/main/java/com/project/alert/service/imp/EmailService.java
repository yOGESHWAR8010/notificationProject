package com.project.alert.service.imp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmailNotification(String subject, String message, String mail) {
		try {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(getUserEmailById(mail));
			if (subject.equals(null) || subject.isBlank()) {
				email.setSubject("Welcome to जैविक कृषि !");
			} else {
				email.setSubject(subject);

			}
			email.setText(message);
			mailSender.send(email);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Mock method to get user email by userId, replace with actual implementation
	private String getUserEmailById(String userId) {

		return userId;
	}

	public boolean sendDelayedEmail(String userId, String password, String recipientEmail) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.schedule(() -> sendEmailNotification(userId, password, recipientEmail), 2, TimeUnit.MINUTES);
		scheduler.shutdown();
		return true;
	}

}
