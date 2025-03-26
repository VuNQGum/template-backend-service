package com.evnit.ttpm.stdv.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * AuthUserAuthority entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AUTH_USER_AUTHORITY", schema = "dbo")
public class UserAuthority implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserAuthorityId id;
	private Integer donviId;

	private String userOld;
	private Boolean isNew = true;
	private String roleConfig;
	private String keyConfig;
	private String nghiepvu;
	private String value1;
	private String value2;

	@Transient
	public String getUserOld() {
		return userOld;
	}

	public void setUserOld(String userOld) {
		this.userOld = userOld;
	}

	@Transient
	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	@Transient
	public String getRoleConfig() {
		return roleConfig;
	}

	public void setRoleConfig(String roleConfig) {
		this.roleConfig = roleConfig;
	}

	@Transient
	public String getKeyConfig() {
		return keyConfig;
	}

	public void setKeyConfig(String keyConfig) {
		this.keyConfig = keyConfig;
	}

	@Transient
	public String getNghiepvu() {
		return nghiepvu;
	}

	public void setNghiepvu(String nghiepvu) {
		this.nghiepvu = nghiepvu;
	}

	@Transient
	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	@Transient
	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	// Constructors

	/** default constructor */
	public UserAuthority() {
	}

	/** minimal constructor */
	public UserAuthority(UserAuthorityId id) {
		this.id = id;
	}

	/** full constructor */
	public UserAuthority(UserAuthorityId id, Integer donviId) {
		this.id = id;
		this.donviId = donviId;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false, length = 50)),
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false)) })
	public UserAuthorityId getId() {
		return this.id;
	}

	public void setId(UserAuthorityId id) {
		this.id = id;
	}

	@Column(name = "Donvi_id")
	public Integer getDonviId() {
		return this.donviId;
	}

	public void setDonviId(Integer donviId) {
		this.donviId = donviId;
	}

}