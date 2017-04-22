package com.application.social.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

public class User implements Serializable{
	private int userId;
	private String userName;
	private String email;	
	private String token;
	private int source;
	private String profilePic;
	private String fbGoId;
	private long created;
	private long modified;
	private int isVerified;
	private String facebookData;
	private String fbPermission;
	
	
	public String getFacebookData() {
		return facebookData;
	}

	public void setFacebookData(String facebookData) {
		this.facebookData = facebookData;
	}
	public String getFbPermission() {
		return fbPermission;
	}

	public void setFbPermission(String fbPermission) {
		this.fbPermission = fbPermission;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}
	public int getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}

	public long getModified() {
		return modified;
	}

	public void setModified(long modified) {
		this.modified = modified;
	}

	

	public String getFbGoId() {
		return fbGoId;
	}

	public void setFbGoId(String fbGoId) {
		this.fbGoId = fbGoId;
	}

	public User() {
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
}
