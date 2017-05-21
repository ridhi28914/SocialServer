package com.application.social.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import com.application.social.controller.ConnectedAccountsDao;
import com.application.social.controller.FacebookDao;
import com.application.social.model.ConnectedAccounts;
import com.application.social.model.Facebook;

@Path("/facebook")
public class FacebookUser extends BaseResource {
	public static final String LOGGER = "FacebookUser.class";

	public FacebookUser() {
		super(FacebookUser.LOGGER);
		
	}
	@Path("/login")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("application/x-www-form-urlencoded")
	public String facebookauth(@FormParam("name")String userName,@FormParam("fbGoId")String platformId
			,@FormParam("profile_pic")String profilePic,@FormParam("client_id")String clientId
			,@FormParam("app_type")String appType,@FormParam("email")String email,
			@FormParam("token")String token,@FormParam("user_id")int userId) throws JSONException {
			
			//		
			if (clientId == null || appType == null)
			return "FAILURE1";
		
			String clientCheck = super.clientCheck(clientId, appType);
			if (clientCheck != null && !clientCheck.equals("success"))
				return "FAILURE2";
//				return CommonLib.getResponseString("Invalid params", "", CommonLib.RESPONSE_INVALID_PARAMS);

			FacebookDao facebookDao= new FacebookDao();
			
			Facebook facebook=null;

			ConnectedAccountsDao connectedAccountsDao= new ConnectedAccountsDao();
			
			
			if (facebook == null){
				
				facebook= facebookDao.getFacebookDetails(platformId);
				
				ConnectedAccounts connectedAccounts = null; 
				connectedAccounts = connectedAccountsDao.getConnectedAccountsDetails(userId);
				if(connectedAccounts==null || connectedAccounts.getUserId()<=0) {
						ConnectedAccounts userToAdd = new ConnectedAccounts(); 
						userToAdd.setUserId(userId);
						userToAdd.setFacebookId(platformId);
						connectedAccounts = connectedAccountsDao.addConnectedAccountsDetails(userToAdd);
				}
				else{	
					connectedAccounts.setFacebookId(platformId);
					connectedAccounts=connectedAccountsDao.updateConnectedAccountsDetails(connectedAccounts,2);
				}
				
				if (facebook == null || facebook.getFacebookId() <= 0) {

											
					Facebook userToAdd = new Facebook();
					userToAdd.setProfilePic(profilePic);
					userToAdd.setUserName(userName);
					userToAdd.setPlatformId(platformId);
					userToAdd.setCreated(System.currentTimeMillis());
					userToAdd.setModified(0);
					userToAdd.setIsVerified(1);
					facebook = facebookDao.addFacebookDetails(userToAdd);	
				}
			}
			if (facebook == null || facebook.getFacebookId() <= 0)
				return "FAILURE3";
			return "SUCCESS";
			
			
		}
}
