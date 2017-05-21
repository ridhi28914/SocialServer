package com.application.social.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.application.social.controller.ConnectedAccountsDao;
import com.application.social.controller.TwitterDao;
import com.application.social.model.ConnectedAccounts;
import com.application.social.model.Twitter;
import com.application.social.model.User;
import com.application.social.util.CommonLib;

@Path("/twitter")
public class TwitterUser extends BaseResource {

	public final static String LOGGER = "TwitterUser.class" ;

	public TwitterUser() {
		super(TwitterUser.LOGGER);
	}
	@Path("/login")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("application/x-www-form-urlencoded")
	public String twitterauth(@FormParam("name")String userName,@FormParam("email")String email
			,@FormParam("fbGoId")String platformId
			,@FormParam("profile_pic")String profilePic,@FormParam("client_id")String clientId
			,@FormParam("app_type")String appType,@FormParam("token")String token,
			@FormParam("user_id")int userId) throws JSONException {
			
			//		
			if (clientId == null || appType == null)
			return "FAILURE";
		
			String clientCheck = super.clientCheck(clientId, appType);
			if (clientCheck != null && !clientCheck.equals("success"))
				return "FAILURE";
//				return CommonLib.getResponseString("Invalid params", "", CommonLib.RESPONSE_INVALID_PARAMS);

			//			todo:// try to get email of twitter
//			if (email == null || email.isEmpty())
//				return CommonLib.getResponseString("Invalid email", "", CommonLib.RESPONSE_INVALID_PARAMS);
			
			TwitterDao twitterDao= new TwitterDao();
			Twitter twitter=null;
			
			ConnectedAccountsDao connectedAccountsDao= new ConnectedAccountsDao();
			
			if (twitter == null || twitter.getTwitterId() <= 0){
				twitter = twitterDao.getTwitterDetails(platformId);
				
				ConnectedAccounts connectedAccounts = null; 
				connectedAccounts = connectedAccountsDao.getConnectedAccountsDetails(userId);
				if(connectedAccounts==null || connectedAccounts.getUserId()<=0) {
						ConnectedAccounts userToAdd = new ConnectedAccounts(); 
						userToAdd.setUserId(userId);
						userToAdd.setTwitterId(platformId);
						connectedAccounts = connectedAccountsDao.addConnectedAccountsDetails(userToAdd);
				}
				else{	
					connectedAccounts.setTwitterId(platformId);
					connectedAccounts=connectedAccountsDao.updateConnectedAccountsDetails(connectedAccounts,0);
				}
				
				if (twitter == null || twitter.getTwitterId() <= 0) {
					
					Twitter userToAdd = new Twitter();
//					userToAdd.setProfilePic(profilePic);
					userToAdd.setUserName(userName);
//					userToAdd.setPassWord(password);
//					userToAdd.setEmail(email);
					userToAdd.setToken(token);
					userToAdd.setPlatformId(platformId);
					userToAdd.setCreated(System.currentTimeMillis());
					userToAdd.setModified(0);
					userToAdd.setIsVerified(1);
					twitter = twitterDao.addTwitterDetails(userToAdd);
					
				}
			}
			if (twitter == null || twitter.getTwitterId() <= 0)
				return "FAILURE";
			return "SUCCESS";
			
			
	}
}
