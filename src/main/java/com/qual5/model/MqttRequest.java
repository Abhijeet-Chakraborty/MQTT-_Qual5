package com.qual5.model;

public class MqttRequest {

	private String ipAddress;
	private String port;
	private String topic;	

	public MqttRequest() {
		super();
	}

	public MqttRequest(String ipAddress, String port, String topic) {
		super();
		this.ipAddress = ipAddress;
		this.port = port;
		this.topic = topic;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
