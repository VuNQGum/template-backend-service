package com.evnit.ttpm.stdv.storage;

import java.io.Serializable;

public class ResponseData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean State;
	private String Message;
	private Object Data;

	public ResponseData() {
		// TODO Auto-generated constructor stub
	}

	public Boolean getState() {
		return State;
	}

	public void setState(Boolean state) {
		State = state;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Object getData() {
		return Data;
	}

	public void setData(Object data) {
		Data = data;
	}

}
