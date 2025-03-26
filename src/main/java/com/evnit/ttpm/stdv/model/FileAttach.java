package com.evnit.ttpm.stdv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.evnit.ttpm.stdv.enums.NGHIEPVUCODE;

/**
 * FileAttach entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FILE_ATTACH", schema = "dbo")
public class FileAttach implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer donviId;
	private Long objectId;
	private NGHIEPVUCODE nghiepvuCode;
	private String fileId;
	private String fileName;
	private String fileType;
	private Long fileSize;
	private String fileExten;
	private String mimeType;
	// private String pathFile;
	private String createUser;
	private String updateUser;
	private Date createTime;
	private Date updateTime;
	private Boolean deleted = false;
	private String fileContent;
//	private String token;

	/** default constructor */
	public FileAttach() {
	}

	/** minimal constructor */
	public FileAttach(Long id) {
		this.id = id;
	}

	/** full constructor */
	public FileAttach(Long id, Integer donviId, Long objectId, NGHIEPVUCODE nghiepvuCode, String fileId,
			String fileName, String fileType, Long fileSize, String fileExten, String mimeType, String pathFile,
			String createUser, String updateUser, Date createTime, Date updateTime, Boolean deleted) {
		this.id = id;
		this.donviId = donviId;
		this.objectId = objectId;
		this.nghiepvuCode = nghiepvuCode;
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.fileExten = fileExten;
		this.mimeType = mimeType;
		// this.pathFile = pathFile;
		this.createUser = createUser;
		this.updateUser = updateUser;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.deleted = deleted;
	}

	// Property accessors
	@Id
	@Column(name = "Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "Donvi_id")
	public Integer getDonviId() {
		return this.donviId;
	}

	public void setDonviId(Integer donviId) {
		this.donviId = donviId;
	}

	@Column(name = "Object_id", precision = 18, scale = 0)
	public Long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "Nghiepvu_code", length = 50)
	public NGHIEPVUCODE getNghiepvuCode() {
		return this.nghiepvuCode;
	}

	public void setNghiepvuCode(NGHIEPVUCODE nghiepvuCode) {
		this.nghiepvuCode = nghiepvuCode;
	}

	@Column(name = "File_id", length = 250)
	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Column(name = "File_name", length = 250)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "File_type", length = 50)
	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "File_size", precision = 18)
	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "File_exten", length = 10)
	public String getFileExten() {
		return this.fileExten;
	}

	public void setFileExten(String fileExten) {
		this.fileExten = fileExten;
	}

	@Column(name = "Mime_type", length = 250)
	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	// @Column(name = "Path_file", length = 250)
	// public String getPathFile() {
	// return this.pathFile;
	// }
	//
	// public void setPathFile(String pathFile) {
	// this.pathFile = pathFile;
	// }

	@Column(name = "Create_user", length = 50)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "Update_user", length = 50)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "Create_time", length = 23)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Update_time", length = 23)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "deleted")
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Transient
	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	
	
//
//	@Transient
//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}

}