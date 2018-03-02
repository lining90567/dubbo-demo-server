package com.ln.demo.api.system.entity;

/**
 * 系统角色实体类
 * 
 * @author Lining
 * @date 2017/10/27
 */
public class Role implements java.io.Serializable {
	
    private static final long serialVersionUID = 3631536115032816649L;

    private Integer id;
	
	private String code;
	
	private String name;
	
	private boolean locked;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getLocked() {
		return this.locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

}
