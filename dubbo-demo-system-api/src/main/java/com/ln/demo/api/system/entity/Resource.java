package com.ln.demo.api.system.entity;

/**
 * 资源实体类
 * 
 * @author Lining
 * @date 2017/10/25
 */
public class Resource implements java.io.Serializable {

    private static final long serialVersionUID = 8286484183880343900L;

    private Integer id;
	
	private String name;
	
	private String code;
	
	private String url;
	
	private String permission;
	
	private boolean locked;
	
	private Router router;
	
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
	
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPermission() {
		return this.permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	public Boolean getLocked() {
		return this.locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	
	public Router getRouter() {
		return this.router;
	}
	public void setRouter(Router router) {
		this.router = router;
	}
	
}
