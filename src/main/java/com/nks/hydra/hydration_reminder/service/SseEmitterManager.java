package com.nks.hydra.hydration_reminder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class SseEmitterManager {
    
    private SseEmitter sseEmitter;

    public void setEmitter(SseEmitter sseEmitter) {
        this.sseEmitter = sseEmitter;
    }

    public SseEmitter getEmitter() {
        return this.sseEmitter;
    }
}
