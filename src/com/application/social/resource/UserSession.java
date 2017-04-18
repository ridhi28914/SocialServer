package com.application.social.resource;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import org.hibernate.Session;

import com.application.social.controller.UserDao;
import com.application.social.controller.UserSessionDao;
import com.application.social.model.User;
import com.application.social.util.CommonLib;
import com.application.social.util.DbUtil;
import com.application.social.util.JsonUtil;
import com.application.social.util.exception.ZException;


@Path("/auth")
public class UserSession extends BaseResource{
	
	public final static String LOGGER = "UserSession.class" ;
	
	public UserSession() {
//		super(classObj);
		super(UserSession.LOGGER);
	}

	/**
	 * Operations - User login using Facebook/Google Operations, Add a new user, Send
	 * an email to the new user Generates a session for the user
	 * 
	 * @author ridhi
	 */

	@Path("/login")
	@POST
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public JSONObject authorization(@FormParam("name")String userName,@FormParam("email")String email
			,@FormParam("source")int source,@FormParam("fbGoID")String fbGoId
			,@FormParam("profile_pic")String profilePic,@FormParam("client_id")String clientId
			,@FormParam("app_type")String appType,@FormParam("token")String token) {
			
			//		
		
		
			String clientCheck = super.clientCheck(clientId, appType);
			if (clientCheck != null && !clientCheck.equals("success"))
				return CommonLib.getResponseString("Invalid params", "", CommonLib.RESPONSE_INVALID_PARAMS);
			
			if (email == null || email.isEmpty())
				return CommonLib.getResponseString("Invalid email", "", CommonLib.RESPONSE_INVALID_PARAMS);


			UserDao userDao = new UserDao();
			User user = null;
			
//			// case of a signup
//			if ((userName != null && !userName.isEmpty()) && (email != null && !email.isEmpty())
//					&& (password != null && !password.isEmpty())) {
//				user = userDao.getUserDetailsFromEmail(email);
//				if (user != null) {
//					return CommonLib.getResponseString("Email already exists", "Invalid signup credentials",
//							CommonLib.RESPONSE_INVALID_PARAMS);
//				}
//			}
			
			// user does not exist
			if ((user == null || user.getUserId() <= 0) && !((token != null && !token.isEmpty())
					|| (userName != null && !userName.isEmpty())))
				return CommonLib.getResponseString("Invalid user", "Invalid login credentials",
						CommonLib.RESPONSE_INVALID_PARAMS);
			
			
			if (user == null || user.getUserId() <= 0){
				user = userDao.getUserDetails(fbGoId);
				if ((user == null || user.getUserId() <= 0))
					user = userDao.getUserDetailsFromEmail(email);
			
				if (user == null || user.getUserId() <= 0) {
					User userToAdd = new User();
					userToAdd.setProfilePic(profilePic);
					userToAdd.setUserName(userName);
//					userToAdd.setPassWord(password);
					userToAdd.setEmail(email);
					userToAdd.setSource(source);
					userToAdd.setToken(token);
					userToAdd.setProfilePic(profilePic);
//					userToAdd.setFacebookId(fbGoId);
//					userToAdd.setFacebookData(fbData);
//					userToAdd.setFbPermission(fb_permissions);
					userToAdd.setCreated(System.currentTimeMillis());
					userToAdd.setModified(0);
					
					user = userDao.addUserDetails(userToAdd);
					
			}
				if (user == null || user.getUserId() <= 0)
					return CommonLib.getResponseString("Error", "Some error occured", CommonLib.RESPONSE_INVALID_PARAMS);

			}
			
			UserSessionDao userSessionDao = new UserSessionDao();
//			// email verification check
			int status = CommonLib.RESPONSE_SUCCESS;
			
			// Generate Access Token
			Object[] tokens = userSessionDao.generateAccessToken(user.getUserName(), user.getUserId() );
//			imei, regId,			location

			String accessToken = (String) tokens[0];
			boolean exists = (Boolean) tokens[1];

			boolean sessionAdded = false;
			if (!exists) {
				sessionAdded = userSessionDao.addSession(user.getUserId(), accessToken);
//				, regId, location, imei
			} else {
				sessionAdded = true;
			}
			if (sessionAdded) {
				JSONObject responseObject = new JSONObject();
				try {
					responseObject.put("access_token", accessToken);
					responseObject.put("user_id", user.getUserId());
					responseObject.put("email", user.getEmail());
					responseObject.put("profile_pic", user.getProfilePic());
					responseObject.put("username", user.getUserName());
//					responseObject.put("points", user.getPoints());
					responseObject.put("user", JsonUtil.getUserJson(user));

				} catch (JSONException e) {
					try {
						throw new ZException("Error", e);
					} catch (ZException e1) {
						e1.printStackTrace();
					}
					error("Jersey exception: " + e.getMessage());
				}
				return CommonLib.getResponseString(responseObject.toString(), "", status);
			} else
				return CommonLib.getResponseString("failed", "", status);
			
	}
	/**
	 * Logout
	 * 
	 * @author ridhi
	 */
	@Path("/logout")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("application/x-www-form-urlencoded")
	public String userLogout(@FormParam("access_token") String accessToken, @FormParam("client_id") String clientId,
			@FormParam("app_type") String appType) {

		// null checks, invalid request
		if (clientId == null || appType == null)
			return "FAILURE";

		String clientCheck = super.clientCheck(clientId, appType);
		if (clientCheck != null && !clientCheck.equals("success"))
			return "FAILURE";

		UserDao userDao = new UserDao();
		User user = userDao.userActive(accessToken);
		boolean returnValue = false;

		if (user != null && user.getUserId() > 0) {
			UserSessionDao sessionDao = new UserSessionDao();
			returnValue = sessionDao.nullifyAccessToken(user.getUserId(), accessToken);
		}

		if (accessToken != null && !returnValue)
			return "FAILURE";

		return "SUCCESS";
	}

}			
			
//		 	String enc= userName+email+userId;
//			String access_token=null;
//			try {
//				access_token = Base64.getEncoder().encodeToString(enc.getBytes("utf-8"));
//			} catch (UnsupportedEncodingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			JSONObject response = new JSONObject();
//			try {
//				response.put("response","success");
//				response.put("access_token",access_token);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			System.out.print(response);
//			return response.toString();
//			return response;
			
			
	
//			
//			DbUtil dbu= new DbUtil();
//			Session session = dbu.getSessionFactory().openSession();
//			session.beginTransaction();
//			Query query = session.createQuery("from UserDetails where userId= :userid");
//			query.setParameter("userid", user_id);
//			
//			UserSession sess= new UserSession();
//			sess.setUserId(user_id);
//			sess.setIsValid(true);
//			sess.setAccessToken(access_token);
//			session.save(sess);
//			
//			if(query.getResultList()!=null){
//			//code of shred preferences	
//			}
//			else{
//				UserDetails user = new UserDetails();
//				user.setUserId(user_id);
//				user.setEmail(email);
//				user.setUserName(name);
////				user.setToken(token);x
//				user.setSource(source);
//				user.setProfilePic(profile_pic);
//				session.save(user);
//				
//			}
//			
			 
		 
		 
			

