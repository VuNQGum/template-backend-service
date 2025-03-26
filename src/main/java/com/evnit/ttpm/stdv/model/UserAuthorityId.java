package com.evnit.ttpm.stdv.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AuthUserAuthorityId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class UserAuthorityId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private Long roleId;
	private String nhomQuyen;

	// Constructors

	/** default constructor */
	public UserAuthorityId() {
	}

	/** full constructor */
	public UserAuthorityId(String userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public UserAuthorityId(String userId, Long roleId, String nhomQuyen) {
		this.userId = userId;
		this.roleId = roleId;
		this.nhomQuyen = nhomQuyen;
	}

	// Property accessors

	@Column(name = "USER_ID", nullable = false, length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ROLE_ID", nullable = false)
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserAuthorityId))
			return false;
		UserAuthorityId castOther = (UserAuthorityId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null
						&& castOther.getRoleId() != null && this.getRoleId()
						.equals(castOther.getRoleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		return result;
	}

	@Column(name = "Nhom_quyen")
	public String getNhomQuyen() {
		return nhomQuyen;
	}

	public void setNhomQuyen(String nhomQuyen) {
		this.nhomQuyen = nhomQuyen;
	}

}