package org.hdcd.vo;

import org.springframework.web.multipart.MultipartFile;

public class FileMember {
	private String userId;
	private String password;
	private MultipartFile picture;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
}
