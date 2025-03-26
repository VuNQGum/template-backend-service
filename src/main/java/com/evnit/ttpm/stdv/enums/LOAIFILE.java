package com.evnit.ttpm.stdv.enums;

public enum LOAIFILE {

	DINH_KEM("DINH_KEM"), BANG_KHEN("BANG_KHEN"), QUYET_DINH("QUYET_DINH"), FILE_ANH("FILE_ANH"),
	BAO_CAO("BAO_CAO");

	private String value;

	LOAIFILE(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
