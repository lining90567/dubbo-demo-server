package com.ln.demo.api.system.dto;

import java.util.Date;

/**
 * 用户DTO类
 * @author Lining
 * @date 2017/11/15
 */
public final class UserDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	private String loginName;
	
	private String salt;
	
	private String password;
	
	private boolean locked;
	
	private Date createdAt;
	
	private int creatorId;
	
	private String creatorName;
	
	private int[] roleIds;
	
	
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
	
	public int getCreatorId() {
		return this.creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	
	public String getCreatorName() {
	    return this.creatorName;
	}
	public void setCreatorName(String creatorName) {
	    this.creatorName = creatorName;
	}
	
	public int[] getRoleIds() {
	    return this.roleIds;
	}
	public void setRoleIds(int[] roleIds) {
	    this.roleIds = roleIds;
	}

}
