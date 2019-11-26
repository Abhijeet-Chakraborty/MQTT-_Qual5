package com.qual5.mqttserviceImpl;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.qual5.model.ResponseData;
import com.qual5.utils.BeanUtil;


public class RFIDMqttCallback implements MqttCallback {
	
	SimpMessagingTemplate simpMessage = null;
	
	@Override
	public void connectionLost(Throwable arg0) {
		System.out.println("Connection to MQTT broker lost!");
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {

	}

	@Override
	public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
		System.out.println("Message received from topic\t"+ topic + " is\t" + new String(mqttMessage.getPayload()));
		if(simpMessage == null) {
			simpMessage = BeanUtil.getBean(SimpMessagingTemplate.class);
		}
		simpMessage.convertAndSend("/topic/websocket", new ResponseData(new String(mqttMessage.getPayload())));
	}
}
