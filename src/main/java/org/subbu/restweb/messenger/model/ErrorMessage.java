package org.subbu.restweb.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private String msg;
	private int statusCode;
	private String URL;
	public ErrorMessage(){
		
	}
	
	public ErrorMessage(String msg, int statusCode, String URL) {
		this.msg=msg;
		this.statusCode=statusCode;
		this.URL = URL;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
}
