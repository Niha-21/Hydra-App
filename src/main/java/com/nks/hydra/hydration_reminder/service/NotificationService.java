package com.nks.hydra.hydration_reminder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class NotificationService {

    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> reminderTask;
    private ScheduledFuture<?> heartbeatTask;

    public void setEmitter(SseEmitter emitter) {
        
        scheduler = Executors.newSingleThreadScheduledExecutor();

        reminderTask = scheduler.scheduleAtFixedRate(() -> {
            try {
                emitter.send(SseEmitter.event().name("reminder").data("💧 Drink water to stay hydrated!"));
            } catch (Exception e) {
                System.out.println("Exception in sending reminder!");
                emitter.completeWithError(e);
                cancelTasks();
            }
        }, 0, 5, TimeUnit.SECONDS);

        heartbeatTask = scheduler.scheduleAtFixedRate(() -> {
            try {
                emitter.send(SseEmitter.event().name("heartbeat").data("ping"));
                System.out.println("Sending heartbeat");
            } catch (Exception e) {
                System.out.println("Exception in sending heartbeat!");
                emitter.completeWithError(e);
                cancelTasks();
            }
        }, 0, 25, TimeUnit.SECONDS);

        emitter.onCompletion(this::cancelTasks);
        emitter.onTimeout(() -> {
            emitter.complete();
            cancelTasks();
        });
    }

    private void cancelTasks() {
        if (reminderTask != null) reminderTask.cancel(true);
        if (heartbeatTask != null) heartbeatTask.cancel(true);
        if (scheduler != null) scheduler.shutdownNow();
    }
}
