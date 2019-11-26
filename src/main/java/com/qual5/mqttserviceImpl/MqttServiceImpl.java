package com.qual5.mqttserviceImpl;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import com.qual5.mqttservice.IMqttService;

@Service
public class MqttServiceImpl implements IMqttService{

	private MqttClient mqttClient = null;
	private final String IP_BASE = "tcp://";
	
	@Override
	public void startSubscription(String ipAddress, String port, String topic) {
		try {
			String serverIPAddress = IP_BASE + ipAddress + ":" + port;
			mqttClient = new MqttClient(serverIPAddress, MqttClient.generateClientId());
			mqttClient.setCallback(new RFIDMqttCallback());
			mqttClient.connect();
			mqttClient.subscribe(topic);
		} catch (MqttException e) {			
			e.printStackTrace();
		}
	}

	@Override
	public void stopSubscription() {
		try {
			if(mqttClient != null) {
				mqttClient.disconnect();
				//mqttClient.close();
			}			
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
