package com.evnit.ttpm.stdv.enums;

public enum NGHIEPVUCODE {
	L_VTRICDANH_MOTA("File mô tả vị trí chức danh");

	private String value;

	NGHIEPVUCODE(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
