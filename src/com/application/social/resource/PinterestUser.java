package com.application.social.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import com.application.social.controller.ConnectedAccountsDao;
import com.application.social.controller.PinterestDao;
import com.application.social.model.ConnectedAccounts;
import com.application.social.model.Pinterest;

@Path("/pinterest")
public class PinterestUser extends BaseResource {

	protected static final String LOGGER = "PinterestUser.class";

		public PinterestUser() {
			super(PinterestUser.LOGGER);
		}
		@Path("/login")
		@POST
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes("application/x-www-form-urlencoded")
		public String pinterestauth(@FormParam("name")String userName,@FormParam("fbGoId")String platformId
				,@FormParam("profile_pic")String profilePic,@FormParam("client_id")String clientId
				,@FormParam("app_type")String appType,
				@FormParam("user_id")int userId) throws JSONException {
				
				//		
				if (clientId == null || appType == null)
				return "FAILURE";
			
				String clientCheck = super.clientCheck(clientId, appType);
				if (clientCheck != null && !clientCheck.equals("success"))
					return "FAILURE";
//					return CommonLib.getResponseString("Invalid params", "", CommonLib.RESPONSE_INVALID_PARAMS);

				PinterestDao pinterestDao= new PinterestDao();
				Pinterest pinterest=null;

				ConnectedAccountsDao connectedAccountsDao= new ConnectedAccountsDao();
				
				
				if (pinterest == null){
					
					pinterest= pinterestDao.getPinterestDetails(platformId);
					
					ConnectedAccounts connectedAccounts = null; 
					connectedAccounts = connectedAccountsDao.getConnectedAccountsDetails(userId);
					if(connectedAccounts==null || connectedAccounts.getUserId()<=0) {
							ConnectedAccounts userToAdd = new ConnectedAccounts(); 
							userToAdd.setUserId(userId);
							userToAdd.setPinterestId(platformId);
							connectedAccounts = connectedAccountsDao.addConnectedAccountsDetails(userToAdd);
					}
					else{	
						connectedAccounts.setPinterestId(platformId);
						connectedAccounts=connectedAccountsDao.updateConnectedAccountsDetails(connectedAccounts,1);
					}
					
					if (pinterest == null || pinterest.getPinterestId() <= 0) {

												
						Pinterest userToAdd = new Pinterest();
						userToAdd.setProfilePic(profilePic);
						userToAdd.setUserName(userName);
						userToAdd.setPlatformId(platformId);
						userToAdd.setCreated(System.currentTimeMillis());
						userToAdd.setModified(0);
						userToAdd.setIsVerified(1);
						pinterest = pinterestDao.addPinterestDetails(userToAdd);	
					}
				}
				if (pinterest == null || pinterest.getPinterestId() <= 0)
					return "FAILURE";
				return "SUCCESS";
				
				
			}

}
