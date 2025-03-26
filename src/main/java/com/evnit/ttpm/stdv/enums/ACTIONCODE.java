package com.evnit.ttpm.stdv.enums;

public enum ACTIONCODE {
	SOANTHAO("Soạn thảo"), DANGKY("Đăng ký"), DUYET("Phê duyệt"), KYSO("Ký số"), KIEMTRA("Kiểm tra"), PHANXE(
			"Phân xe"), THEODOI("Theo dõi"), CHAMCONG("Chấm công"), THANHTOAN("Lập bảng công tác phí"), XACNHAN_GIAYDD(
					"Ký số giấy đi đường"), KYSO_GIAY_DIDUONG("Ký số giấy đi đường"), DUYET_GIAYDD(
							"Duyệt giấy đi đường"), CAPVEMAYBAY("Cấp vé máy bay"), KYGGD("Ký giấy đi đường"), CAPSO("Cấp số"), NONE("NONE"), CHUYEN_DO("Chuyển DOFFICE ký"), QTRINH_DO("Quy trình DOFFICE");

	private String value;

	ACTIONCODE(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
