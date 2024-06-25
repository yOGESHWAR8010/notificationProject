package com.project.alert.service;

public interface NotificationService {
    boolean sendEmailNotification(String userId, String message,String mail);
    boolean sendSMSNotification(String userId, String message);
    boolean sendInAppNotification(String userId, String message);
    // Add more methods for other types of notifications if needed
}

