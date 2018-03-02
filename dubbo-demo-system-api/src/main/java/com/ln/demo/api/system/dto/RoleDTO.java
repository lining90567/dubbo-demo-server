package com.ln.demo.api.system.dto;

/**
 * 角色DTO类
 * 
 * @author Lining
 * @date 2018/2/4
 */
public final class RoleDTO implements java.io.Serializable {

    private static final long serialVersionUID = 7358013142957971759L;

    private Integer id;

    private String code;

    private String name;

    private Boolean locked;

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
