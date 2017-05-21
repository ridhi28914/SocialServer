package com.application.social.model;

import java.io.Serializable;

public class ConnectedAccounts implements Serializable{
	
	private int connectedId;
	private int userId;
	private String pinterestId;
	private String twitterId;
	private String facebookId;
	private String instagramId;
	
	public int getConnectedId() {
		return connectedId;
	}
	public void setConnectedId(int connectedId) {
		this.connectedId = connectedId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPinterestId() {
		return pinterestId;
	}
	public void setPinterestId(String pinterestId) {
		this.pinterestId = pinterestId;
	}
	public String getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	public String getInstagramId() {
		return instagramId;
	}
	public void setInstagramId(String instagramId) {
		this.instagramId = instagramId;
	}
	
	
	
	
}
