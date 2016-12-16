package org.subbu.restweb.messenger.messages;

import java.net.URI;
import java.security.URIParameter;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.subbu.restweb.messenger.beans.MessageFilterBean;
import org.subbu.restweb.messenger.exception.DataNotFoundException;
import org.subbu.restweb.messenger.model.Message;
import org.subbu.restweb.messenger.services.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET	
	public List<Message> getAllMessages(@BeanParam MessageFilterBean messageFilterBean){
		if(messageFilterBean.getYear()>0)
			return messageService.getAllMessagesForYear(messageFilterBean.getYear());
		if(messageFilterBean.getStart()>0 && messageFilterBean.getSize()>0)
			return messageService.getAllMessagesFilter(messageFilterBean.getStart(), messageFilterBean.getSize());
		
		return messageService.getAllMessages();
		
	}
	
	@GET	
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) throws DataNotFoundException{		
		Message message = messageService.getMessage(messageId);	
		message.getLinks().add(message.getLinkUrl(getUrlSelf(message,uriInfo), "self"));
		message.getLinks().add(message.getLinkUrl(getUrlProfile(message,uriInfo), "profile"));
					
		return message;
	}

	private String getUrlProfile(Message message,UriInfo uriInfo) {
		String url = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(String.valueOf(message.getAuthor()))
				.build().toString();
		return url;
	}

	private String getUrlSelf(Message message, UriInfo uriInfo) {
		String url = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(String.valueOf(message.getId()))
				.build().toString();
		return url;
	}
	
	@POST	
	@Path("/{msgAuthor}")
	public Message addMessage(@PathParam("msgAuthor") String msgAuthor){
		Message msg = new Message();
		msg.setAuthor(msgAuthor);
		return messageService.addMessage(msg);	
		
	}
		
	@DELETE	
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId") long messageId){		
		return messageService.removeMessage(messageId);
	}
	
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId,Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
		
	}
	
	
	
	@POST	
	public Response addMessages(Message message, @Context UriInfo uriInfo){
		Message newMessage = messageService.addMessages(message);
		URI uri= uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();		
		return Response.created(uri)
						.entity(newMessage)
						.build();
		
		
	}
	

}
