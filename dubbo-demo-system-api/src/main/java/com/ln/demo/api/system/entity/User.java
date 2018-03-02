package com.ln.demo.api.system.entity;

import java.util.Date;

/**
 * 用户实体类
 * 
 * @author Lining
 * @date 2017/9/28
 */
public class User implements java.io.Serializable {

    private static final long serialVersionUID = -654281045807461943L;

    private Integer id;
	
	private String name;
	
	private String loginName;
	
	private String salt;
	
	private String password;
	
	private boolean locked;
	
	private Date createdAt;
	
	private User createdBy;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLoginName() {
		return this.loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getSalt() {
		return this.salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getLocked() {
		return this.locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public Date getCreatedAt() {
		return this.createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public User getCreatedBy() {
		return this.createdBy;
	}

}
