/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.evnit.ttpm.stdv.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

@Entity(name = "im_users_usr")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 74597879236597717L;

	@Id
	@Column(name = "id_usr", unique = true)
	private String username;

	@Column(name = "name_usr", length = 50)
	private String name;

	@Column(name = "NS_ID")
	private Long nsId;

	@Column(name = "id_org_usr")
	private Integer donviId;

	@NaturalId
	@Column(name = "email_usr", unique = true)
	private String email;

	@Column(name = "password_usr")
	private String password;

	@Column(name = "date_create")
	private Instant createdAt;

	@Column(name = "date_update")
	private Instant updatedAt;

	@Column(name = "usr_enabled", nullable = false)
	private Boolean active;

	@Column(name = "usr_token", nullable = false)
	private String usrToken;

//	@Column(name = "type_usr", insertable = true, updatable = true, nullable = true)
//	private Short type;

	@Column(name = "Alias_CA", insertable = true, updatable = true, nullable = true)
	private String aliasCA;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "AUTH_USER_AUTHORITY", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "id_usr") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") })
	private Set<Role> roles = new HashSet<>();

	@Column(name = "Serial_Number_CA", insertable = true, updatable = true, nullable = true)
	private String serialNumberCA;

//	@Column(name = "iddept_usr", insertable = true, updatable = true, nullable = true)
//	private Long iddept;

	@Transient
	private Long expiration = 0L;
	@Transient
	private Integer donviIdRoot = null;

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public Integer getDonviIdRoot() {
		if (donviIdRoot != null)
			return this.donviIdRoot;
		else
			return donviId;
	}

	public void setDonviIdRoot(Integer donviIdRoot) {
		this.donviIdRoot = donviIdRoot;
	}

	public User() {
		super();
	}

	public User(User user) {
		// id = user.getId(); firstName
		name = user.getName();
		username = user.getUsername();
		password = user.getPassword();
		email = user.getEmail();
		active = user.getActive();
		usrToken = user.getUsrToken();
		roles = user.getRoles();
		nsId = user.getNsId();
		donviId = user.getDonviId();
	}

	public void addRole(Role role) {
		roles.add(role);
		role.getUserList().add(this);
	}

	public void addRoles(Set<Role> roles) {
		roles.forEach(this::addRole);
	}

	public void removeRole(Role role) {
		roles.remove(role);
		role.getUserList().remove(this);
	}

	public Integer getDonviId() {
		return donviId;
	}

	public void setDonviId(Integer donviId) {
		this.donviId = donviId;
	}

	public Long getNsId() {
		return nsId;
	}

	public void setNsId(Long nsId) {
		this.nsId = nsId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getUsrToken() {
		return this.usrToken;
	}

	public void setUsrToken(String usrToken) {
		this.usrToken = usrToken;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> authorities) {
		roles = authorities;
	}

//	public Short getType() {
//		return type;
//	}
//
//	public void setType(Short type) {
//		this.type = type;
//	}

	public String getAliasCA() {
		return aliasCA;
	}

	public void setAliasCA(String aliasCA) {
		this.aliasCA = aliasCA;
	}

	public String getSerialNumberCA() {
		return serialNumberCA;
	}

	public void setSerialNumberCA(String serialNumberCA) {
		this.serialNumberCA = serialNumberCA;
	}

//	public Long getIddept() {
//		return iddept;
//	}
//
//	public void setIddept(Long iddept) {
//		this.iddept = iddept;
//	}

	@Override
	public String toString() {
		return "User{" + ", email='" + email + '\'' + ", username='" + username + '\'' + ", password='" + password
				+ '\'' + ", active=" + active + ", roles=" + roles + ", usrToken=" + usrToken + '}';
	}

	// @Override
	// public String toString() {
	// return "User{" + "id=" + id + ", email='" + email + '\'' + ", username='"
	// + username + '\'' + ", password='"
	// + password + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" +
	// lastName + '\'' + ", active="
	// + active + ", roles=" + roles + ", isEmailVerified=" + isEmailVerified +
	// '}';
	// }
}
