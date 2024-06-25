package com.project.alert.service.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.alert.constant.Smsconstant;
import com.project.alert.model.UserLoginDetails;
import com.project.alert.repo.UserLoginDetailsRepo;
import com.project.alert.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	EmailService email;

	UserLoginDetails loginDetails= new UserLoginDetails();
	@Autowired
	UserLoginDetailsRepo repo;

	@Override
	public boolean sendEmailNotification(String subject, String message, String mail) {
		// TODO Auto-generated method stub
		boolean sendEmailNotification = email.sendEmailNotification(subject, message, mail);
		if (sendEmailNotification) {

			LocalDateTime currentDateTime = LocalDateTime.now();
			Long unixTimestamp = currentDateTime.toEpochSecond(ZoneOffset.UTC);
			String unique = String.valueOf(unixTimestamp).substring(0, 4);
			String username = mail.split("@")[0] + unique;
			String userIdPassword = Smsconstant.USER_ID_PASSWORD.replace("[userId]", username).replace("[password]",
					unixTimestamp + "");

			boolean sendDelayedEmail = email.sendDelayedEmail(Smsconstant.SUBJECT, userIdPassword, mail);

			if (sendDelayedEmail) {

				loginDetails.setPassword(unixTimestamp + "");
				loginDetails.setUserId(subject);
				loginDetails.setUserName(username);

				repo.save(loginDetails);

			}

		}
		return sendEmailNotification;
	}

	@Override
	public boolean sendSMSNotification(String userId, String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendInAppNotification(String userId, String message) {
		// TODO Auto-generated method stub
		return false;
	}
}
