package com.challenge.springbootchallenge.service;

import com.challenge.springbootchallenge.model.User;

public interface NotificationService {
    public void sendNotification(User user, String subject, String msg);
}
