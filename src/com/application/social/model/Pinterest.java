package com.application.social.model;

import java.io.Serializable;

public class Pinterest implements Serializable{
	private int pinterestId;
	private String userName;
//	private String token;
	private String profilePic;
	private String platformId;
	private long created;
	private long modified;
	private int isVerified;
	public int getPinterestId() {
		return pinterestId;
	}
	public void setPinterestId(int pinterestId) {
		this.pinterestId = pinterestId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
//	public String getToken() {
//		return token;
//	}
//	public void setToken(String token) {
//		this.token = token;
//	}
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
