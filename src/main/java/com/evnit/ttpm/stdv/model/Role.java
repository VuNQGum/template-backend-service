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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The type Role. Defines the role and the list of users who are associated with
 * that role
 */
@Entity(name = "AUTH_ROLE")
public class Role {

	@Id
	@Column(name = "ROLE_ID")
	private Long id;

	@Column(name = "ROLE_NAME")
	private String role;
//	@Enumerated(EnumType.STRING)
//	@NaturalId
//	private RoleName role;

	@Column(name = "APP_URL")
	private String appURL;

	@Column(name = "APP_NAME")
	private String appName;

	@Column(name = "APP_ICON")
	private String appIcon;
	
	@Column(name = "APP_ACTIVE")
	private Boolean appActive;

	@Column(name = "APP_POSITION")
	private String position;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<User> userList = new HashSet<>();

	public Role(String role) {
		this.role = role;
	}

	public Role() {

	}

	public boolean isAdminRole() {
		return null != this && this.role.equals("ROLE_ADMIN");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAppURL() {
		return appURL;
	}

	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppIcon() {
		return appIcon;
	}
	
	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}
	
	public Boolean getAppActive() {
		return appActive;
	}

	public void setAppActive(Boolean appActive) {
		this.appActive = appActive;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Set<User> getUserList() {
		return userList;
	}

	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}
}
