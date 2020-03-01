package Tjoker.pojo;

import java.sql.Date;

public class FileUpDown {
	private String id;

	private String uuidname;//上传文件的名称，文件的uuid名

	private String filename;//上传文件的真实名称
	
	private String savepath; //记住文件的位置

	private Date uploadtime; //文件的上传时间

	private String description;//文件的描述

	private String username;
	

	@Override
	public String toString() {
		return "File [id=" + id + ", uuidname=" + uuidname + ", filename=" + filename + ", savepath=" + savepath
				+ ", uploadtime=" + uploadtime + ", description=" + description + ", username=" + username + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuidname() {
		return uuidname;
	}

	public void setUuidname(String uuidname) {
		this.uuidname = uuidname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSavepath() {
		return savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	
}
