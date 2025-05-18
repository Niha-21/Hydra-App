package com.nks.hydra.hydration_reminder.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@Service
public class NotificationService {

    private SseEmitter emitter;

    public void setEmitter(SseEmitter emitter) {
        this.emitter = emitter;
    }

    @Scheduled (fixedRate = 5000)
    public void setReminder() {
        if(emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("reminder").data("💧 Drink water to stay hydrated!"));
            } catch (Exception e) {
                System.out.println("Exception in sending reminder!");
                emitter.completeWithError(e);
            }
        }
    }

}
