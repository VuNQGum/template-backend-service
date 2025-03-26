/*
 * (c) 2008 EVNIT - FMIS
 * Create - DongNH - 16/06/2008
 * Update - DongNH - 09/07/2008 
 */

package com.evnit.ttpm.stdv.util;

import java.util.List;
import java.util.Vector;

/**
 * Class ghi lỗi ứng dụng chung
 * 
 * @author FMIS (c) 2008 EVNIT
 */
public class FApplicationException extends Exception {
	private static final long serialVersionUID = 1L;
	private String idMessage;
	private List<Object> argumentList = new Vector<Object>();

	public FApplicationException() {
		super();
	}

	public FApplicationException(String message) {
		super(message);
		this.idMessage = message;
	}

	public FApplicationException(Throwable cause) {
		super(cause);
	}

	public FApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}

	public List<Object> getArgumentList() {
		return argumentList;
	}

	public void setArgumentList(List<Object> argumentList) {
		this.argumentList = argumentList;
	}
}
