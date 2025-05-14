package com.nks.hydra.hydration_reminder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class NotificationService {

    private SseEmitter emitter;

    // Method to send periodic hydration reminders
    public void sendPeriodicNotifications() {
        // Creating a thread to send periodic reminders
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            if (emitter != null) {
                try {
                    emitter.send(SseEmitter.event().name("reminder").data("Drink water to stay hydrated!"));
                } catch (Exception e) {
                    emitter.completeWithError(e);
                }
            }
        }, 0, 5, TimeUnit.SECONDS);  // sending reminder every 5 seconds
    }

    
    public void setEmitter(SseEmitter emitter) {
        this.emitter = emitter;
    }
}
