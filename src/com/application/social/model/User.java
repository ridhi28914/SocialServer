package com.application.social.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity 
//@Table (name="USER")
public class User implements Serializable{

//	@Column (name="USER_ID")
	private int userId;
//	@Column (name="USER_NAME")
	private String userName;
//	@Column (name="EMAIL")
	private String email;
//	@Column (name="TOKEN")
	private String token;
//	@Column (name="SOURCE")
	private int source; // fb(0),google(1)?
//	@Column (name="PROFILE_PIC")
	private String profilePic;
	private String fbGoId;
	private long created;
	private long modified;
	private int isVerified;
	
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
