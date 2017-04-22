	package com.application.social.util;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.application.social.model.User;
import com.application.social.util.exception.ZException;

public class CommonLib {
	public static final String ANDROID_CLIENT_ID = "social_android_client";
	public static final String ANDROID_APP_TYPE = "social_android";
	
	public static final boolean ZLOG = false;
	
	public static final String VERSION = "v1";

	public static final String ZAPP_ID = "hello@social.com";
	public static final String ZAPP_PWD = "hello.social";
	
	public static final int RESPONSE_FAILURE = 200;
	public static final int RESPONSE_SUCCESS = 201;
	public static final int RESPONSE_INVALID_USER = 202;
	public static final int RESPONSE_INVALID_PARAMS = 203;
	public static final int RESPONSE_INVALID_APP_TYPE = 204;
	public static final int RESPONSE_INVALID_CLIENT_ID = 205;
	public static final int RESPONSE_INVALID_MERCHANT = 206;

	
	public static JSONObject getResponseString(Object responseJson, String errorMessage, int status) throws JSONException {
		JSONObject responseObject = new JSONObject();
		switch (status) {
		case RESPONSE_SUCCESS:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "success");
				responseObject.put("errorCode", "0");
				responseObject.put("errorMessage", "");
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;

		case RESPONSE_INVALID_USER:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "failure");
				responseObject.put("errorCode", RESPONSE_INVALID_USER);
				responseObject.put("errorMessage", errorMessage);
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case RESPONSE_INVALID_PARAMS:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "failure");
				responseObject.put("errorCode", RESPONSE_INVALID_PARAMS);
				responseObject.put("errorMessage", errorMessage);
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case RESPONSE_INVALID_APP_TYPE:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "failure");
				responseObject.put("errorCode", RESPONSE_INVALID_APP_TYPE);
				responseObject.put("errorMessage", errorMessage);
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case RESPONSE_INVALID_CLIENT_ID:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "failure");
				responseObject.put("errorCode", RESPONSE_INVALID_CLIENT_ID);
				responseObject.put("errorMessage", errorMessage);
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case RESPONSE_FAILURE:
			try {
				responseObject.put("response", responseJson);
				responseObject.put("status", "failure");
				responseObject.put("errorCode", RESPONSE_FAILURE);
				responseObject.put("errorMessage", errorMessage);
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
			break;
		default:
			break;
		}

		return responseObject;
//			return new JSONObject(responseObject.toString());
		
	}
	
	public static String getUserName(User user) {
		if (user == null)
			return "";
		String userName = "";
		if (user.getUserName() == null || user.getUserName().equals("") || user.getUserName().equals("null")) {
			try {
				JSONObject data = new JSONObject(user.getFacebookData());
				if (data.has("name")) {
					String name = String.valueOf(data.get("name"));
					name = name.split(" ")[0];
					userName = name;
				}
			} catch (JSONException e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				try {
					throw new ZException("Error", e);
				} catch (ZException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			String name = user.getUserName();
			name = name.split(" ")[0];
			userName = name;
		}
		return userName;
	}
}
