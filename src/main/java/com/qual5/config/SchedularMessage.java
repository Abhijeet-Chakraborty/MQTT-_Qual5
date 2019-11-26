package com.qual5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.qual5.model.ResponseData;

@EnableScheduling
@Configuration
public class SchedularMessage {
	
	@Autowired
	SimpMessagingTemplate template;
	
	//@Scheduled(fixedDelay=3000)
	public void scheduleMessage() {
		template.convertAndSend("/topic/user", new ResponseData("Default Messages"));
	}

}
