package com.application.social.model;

public class Consumer {
	int id;
	String clientId;
	String appType;
	String companyName;
	long registeredOn;

	public Consumer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public long getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(long registeredOn) {
		this.registeredOn = registeredOn;
	}
}
