package com.application.social.util;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.application.social.model.User;

public class JsonUtil {
	public static JSONObject getUserJson(User user) throws JSONException {
		if (user == null)
			return null;
		JSONObject userJsonObject = new JSONObject();

		JSONObject userJson = new JSONObject();
		userJsonObject.put("email", user.getEmail());
		userJsonObject.put("user_id", user.getUserId() + "");
		userJsonObject.put("is_verified", user.getIsVerified());
//		userJsonObject.put("address", user.getAddress());
//		userJsonObject.put("description", user.getBio());
//		userJsonObject.put("phone", user.getPhone());
		userJsonObject.put("profile_pic", user.getProfilePic());
		userJsonObject.put("fbId", user.getFbGoId());
//		userJsonObject.put("contact", user.getPhone());
		userJsonObject.put("user_name", CommonLib.getUserName(user));
		userJson.put("user", userJsonObject);
		return userJson;
	}
}
