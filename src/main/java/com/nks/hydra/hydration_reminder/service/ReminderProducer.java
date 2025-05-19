package com.nks.hydra.hydration_reminder.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class ReminderProducer {
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public static final String TOPIC = "hydration-reminders";

    public void sendReminder(String msg) {
        kafkaTemplate.send(TOPIC, msg);
    }

}
