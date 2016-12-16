package org.subbu.restweb.messenger.messages;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectDemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotations")
	public String useOfParams(@MatrixParam("param") String param,
							 @HeaderParam("authSessionParam") String authSessionParam
							 ){
		return "Matrix Param:"+param+":authSessionParam"+authSessionParam;
		
	}
	
	@GET
	@Path("context")
	public String useOfParamsContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders){
		String path = uriInfo.getAbsolutePath().toString();
		String headers =  httpHeaders.getCookies().toString();
		return "path:"+path+"headers:"+headers;
	}
}
