package com.qual5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.qual5.model.MqttRequest;
import com.qual5.model.ResponseData;
import com.qual5.mqttservice.IMqttService;

@Controller
public class MessageController {
	
	@Autowired
	IMqttService mqttService;
	
	@MessageMapping("/startConnection")
	@SendTo("/topic/websocket")
	public ResponseData getResponse(MqttRequest mqttreq) {	
		mqttService.startSubscription(mqttreq.getIpAddress(), mqttreq.getPort(), mqttreq.getTopic());
		return new ResponseData("Connection Established with || IP-- " + mqttreq.getIpAddress() + " || PORT-- " + mqttreq.getPort() + " || TOPIC-- " + mqttreq.getTopic());
	}
	
	@MessageMapping("/stopConnection")
	@SendTo("/topic/websocket")
	public ResponseData stopConn() {	
		mqttService.stopSubscription();
		return new ResponseData("Disconnected...");
	}
}
