package com.nks.hydra.hydration_reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.nks.hydra.hydration_reminder.service.SseEmitterManager;

import java.io.IOException;

@RestController
public class NotificationController {

    @Autowired
    private SseEmitterManager sseEmitterManager;

    @GetMapping(value = "/notifications/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamNotifications() {

        SseEmitter emitter = new SseEmitter(0L); 
        sseEmitterManager.setEmitter(emitter);

        try {
            emitter.send(SseEmitter.event().name("init").data("Hydra reminder stream started!"));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

}
