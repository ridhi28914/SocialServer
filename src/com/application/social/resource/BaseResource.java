package com.application.social.resource;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.application.social.controller.ConsumerDao;
import com.application.social.util.CommonLib;

public class BaseResource {
	// Logger object
		private Logger logger;
	
	public BaseResource(String classObj) {
		
		if (CommonLib.ZLOG && classObj != null) {
			logger = Logger.getLogger(classObj);
			BasicConfigurator.configure();
		}
	}
	
	public String clientCheck(String clientId, String appType) {
		String retValue = "Invalid params";

		// null checks, invalid request
		if (clientId == null || appType == null)
			return retValue;

		// check for client_id
		if (!(clientId.equals(CommonLib.ANDROID_CLIENT_ID) ))
			retValue = "Invalid client id";

		// check for app type if
		if (!(appType.equals(CommonLib.ANDROID_APP_TYPE) ))
			retValue = "Invalid app type";

		if (retValue.equals("Invalid app type") || retValue.equals("Invalid client id")) {
			ConsumerDao consumerDao = new ConsumerDao();
			boolean clientCheck = consumerDao.checkClient(clientId, appType);
			if (clientCheck)
				return "success";
			else
				return retValue;
		} else
			return "success";
	}

	public void debug(Object message) {
		if (CommonLib.ZLOG && message != null)
			logger.debug(message);
	}

	public void info(Object message) {
		if (CommonLib.ZLOG && message != null)
			logger.info(message);
	}

	public void error(Object message) {
		if (CommonLib.ZLOG && message != null)
			logger.error(message);
	}

	public void fatal(Object message) {
		if (CommonLib.ZLOG && message != null)
			logger.fatal(message);
	}
}
