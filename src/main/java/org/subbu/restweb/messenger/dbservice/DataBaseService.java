package org.subbu.restweb.messenger.dbservice;

import java.util.HashMap;
import java.util.Map;

import org.subbu.restweb.messenger.model.Message;
import org.subbu.restweb.messenger.model.Profile;

public class DataBaseService {
	
	private static Map<Long,Message> messages = new HashMap<>();
	private static Map<String,Profile> profiles = new HashMap<>();
	
	
	public static Map<Long, Message> getMessages() {			
		return messages;
	}
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	
}
