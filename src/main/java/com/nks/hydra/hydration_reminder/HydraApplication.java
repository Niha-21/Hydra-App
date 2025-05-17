package com.nks.hydra.hydration_reminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.nks.hydra.hydration_reminder.service.NotificationService;

@SpringBootApplication
@EnableScheduling
public class HydraApplication {

	@Autowired
	NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(HydraApplication.class, args);
	}
	
}
