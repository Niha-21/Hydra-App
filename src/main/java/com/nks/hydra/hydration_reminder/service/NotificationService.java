package com.nks.hydra.hydration_reminder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    @Autowired
    private ReminderProducer reminderProducer;

    @Scheduled (fixedRate = 5000)
    public void setReminder() {
        reminderProducer.sendReminder("💧 Drink water to stay hydrated!");
    }

}
