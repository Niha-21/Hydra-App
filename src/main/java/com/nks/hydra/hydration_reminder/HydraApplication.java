package com.nks.hydra.hydration_reminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.nks.hydra.hydration_reminder.service.NotificationService;

@SpringBootApplication
@EnableScheduling
public class HydraApplication implements CommandLineRunner {

	@Autowired
	NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(HydraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		notificationService.sendPeriodicNotifications();
	}
}
