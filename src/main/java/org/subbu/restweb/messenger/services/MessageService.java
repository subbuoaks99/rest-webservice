package org.subbu.restweb.messenger.services;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.subbu.restweb.messenger.dbservice.DataBaseService;
import org.subbu.restweb.messenger.exception.DataNotFoundException;
import org.subbu.restweb.messenger.model.ErrorMessage;
import org.subbu.restweb.messenger.model.Message;

public class MessageService {
	
	private static Map<Long,Message> messages = DataBaseService.getMessages();
	
	public MessageService(){
		messages.put(1L, new Message(1L,"Hello World1","subbu"));
		messages.put(2L, new Message(2L,"Hello World2","balu"));
		messages.put(3L, new Message(3L,"Hello World3","chaithu"));
	}
	
	public List<Message> getAllMessages(){		
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){	
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message msg:messages.values()){
			cal.setTime(msg.getCreated());
			if(cal.get(Calendar.YEAR)== year)
				messagesForYear.add(msg);
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesFilter(int start, int size){
		ArrayList<Message> list =  new ArrayList<Message>(messages.values());
		if(start+size >list.size())
			return new ArrayList<Message>();
		return 	list.subList(start, start+size);		
	}
	
	
	
	public Message getMessage(long id) throws DataNotFoundException{
		Message message = null;
		message = messages.get(id);
		if(message == null)
			throw new DataNotFoundException("No Data Found for the id:"+id);	
		return message;
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);		
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		Message message = messages.get(id);
		messages.remove(id);
		return message;
	}
	
	public Message updateMessage(Message message){
		long msg = message.getId();
		ErrorMessage errorMessage = new ErrorMessage("Message Not Found",404,"URL");
		Response response =  Response.status(Status.NO_CONTENT)
						.entity(errorMessage)
						.build();		
		if(!messages.containsKey(msg)){
			System.out.println("msg id not found"+msg);
			throw new WebApplicationException(response);
		}
	
		messages.put(message.getId(), message);
		return messages.get(message.getId());
	}

	public Message addMessages(Message message) {
		message.setId(messages.size()+1);
		messages.put(messages.size()+1L, message);
		return message;
	}


}
