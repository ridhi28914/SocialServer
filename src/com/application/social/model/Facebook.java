package com.application.social.model;

import java.io.Serializable;

public class Facebook implements Serializable{
	
	private int facebookId;
	private String userName;
	private String email;
	private String token;
	private String profilePic;
	private String platformId;
	private long created;
	private long modified;
	private int isVerified;
	public int getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(int facebookId) {
		this.facebookId = facebookId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public long getModified() {
		return modified;
	}
	public void setModified(long modified) {
		this.modified = modified;
	}
	public int getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}
	
}
