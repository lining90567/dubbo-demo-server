package com.ln.demo.vo;

/**
 * 用户列表对象VO类
 * 
 * @author Lining
 * @date 2017/9/28
 */
public final class UserListVO {
    
    private int id;
    
    private String name;
    
    private String loginName;
    
    private boolean locked;
    
    private String createdAt;
    
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
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

}
