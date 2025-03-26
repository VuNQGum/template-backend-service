package com.evnit.ttpm.stdv.enums;

public enum VITRIPHEDUYET {
	CTHDTV("Chủ tịch HĐTV"), TVHDTV("Thành viên HĐTV"), BGIAMDOC("Ban TGĐ/ Ban Giám đốc Công ty"), GIAMDOC(
			"Tổng giám đốc/ Giám đốc"), PGIAMDOC("Phó Tổng giám đốc/ Phó giám đốc"), LDBANPHONG(
					"Lãnh đạo ban/ phòng"), TBANPHONG("Trưởng ban/ phòng"), THUKY("Thư ký");

	private String value;

	VITRIPHEDUYET(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
