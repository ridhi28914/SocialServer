package com.application.social.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/facebook")
public class test {
	@Path("/logi")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes("application/x-www-form-urlencoded")
	public String facebookauth(){
		return "s";
	}
}
