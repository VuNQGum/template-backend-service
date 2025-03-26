package com.evnit.ttpm.stdv.enums;

public enum TRANGTHAI {
	DRAFT("Bản nháp"), 
	SOANTHAO("Soạn thảo"), 	
	DANGPHDUYET("Đang phê duyệt"), 
	KETTHUC("Kết thúc"), 
	CHODUYET("Chờ duyệt"), 
	HUY("Hủy"), 
	DANGDI_CT("Đang đi công tác"), 
	HOANTHANH("Hoàn thành"), 
	CHOBANHANHQD("Chờ ban hành QĐ"),		
	
	CHOTAODUTHAO("Chờ dự thảo"),
	DATAODUTHAO("Chờ chuyển NLĐ ký"),
	DACHUYENNLD("Chờ NLĐ ký số"),
	CHOCHUYENDO("Chờ chuyển dự thảo sang DO"),
	DACHUYENDO("Chờ phát hành trên DO"),
	DABANHANHQD("Đã ban hành QĐ"),
	TRALAI("Trả lại"),
	
	LUONG_CHOCHUYEN_DUTHAO_DO("Chờ chuyển dự thảo sang DO"),
	LUONG_CHO_BANHANH_VB_DO("Chờ ban hành QĐ"),
	LUONG_TRALAI("Trả lại"),
	LUONG_KETTHUC("Đã ban hành QĐ");
	
	private String value;

	TRANGTHAI(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
