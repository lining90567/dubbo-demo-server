package com.ln.demo.vo;

/**
 * 用户详情VO对象
 * 
 * @author Lining
 * @date 2017/9/28
 */
public final class UserDetailVO {

	private Integer id;
	
	private String name;
	
	private String loginName;
	
	private String password;
	
	private boolean locked;
	
	private String createdAt;
	
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
	
	public String getCreatedAt() {
		return this.createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public int getCreatedBy() {
		return this.creatorId;
	}
	
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getCreatorName() {
		return this.creatorName;
	}
	
	public int[] getRoleIds() {
	    return this.roleIds;
	}
	public void setRoleIds(int[] roleIds) {
	    this.roleIds = roleIds;
	}
	
}
