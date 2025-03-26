package com.evnit.ttpm.stdv.storage;

import java.io.Serializable;

public class FileData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1211186352872232711L;

	private String fileName;
	private byte[] byteFile;
	private String base64;

	public FileData() {

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getByteFile() {
		return byteFile;
	}

	public void setByteFile(byte[] byteFile) {
		this.byteFile = byteFile;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}
}
