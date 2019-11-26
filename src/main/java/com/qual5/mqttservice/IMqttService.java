package com.qual5.mqttservice;

public interface IMqttService {
	
	public void startSubscription(String ipAddress, String port, String topic);
	public void stopSubscription();
}
