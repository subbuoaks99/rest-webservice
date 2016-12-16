package org.subbu.restweb.messenger.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.subbu.restweb.messenger.dbservice.DataBaseService;
import org.subbu.restweb.messenger.model.Profile;

public class ProfileService {
	private static Map<String,Profile> profiles = DataBaseService.getProfiles();
	
	public ProfileService(){
		profiles.put("admin", new Profile(1,"admin","subbu","vadakattu"));
	}

	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(String profileName, Profile profile){
		if(profiles.keySet().contains(profileName)){
			profiles.put(profileName, profile);
			return profile;
		}else			
		return null;
	}
	
	public Profile removeProfile(String profileName){		
		profiles.remove(profileName);
		return profiles.get(profileName);
	}
	
}
