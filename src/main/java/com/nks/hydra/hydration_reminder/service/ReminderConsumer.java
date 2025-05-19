package com.nks.hydra.hydration_reminder.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class ReminderConsumer {
    
    @Autowired
    private SseEmitterManager sseEmitterManager;

    @KafkaListener(topics = "hydration-reminders", groupId = "hydra-group")
    public void consumeReminder(String msg) {
        SseEmitter sseEmitter = sseEmitterManager.getEmitter();
        if(sseEmitter != null){
            try {
                sseEmitter.send(SseEmitter.event().name("reminder").data(msg));
            } catch (IOException e) {
                sseEmitter.completeWithError(e);
            }
        }
    }

}
