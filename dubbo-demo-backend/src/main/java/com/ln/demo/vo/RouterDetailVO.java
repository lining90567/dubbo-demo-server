package com.ln.demo.vo;

/**
 * 路由详情vo类
 * 
 * @author Lining
 * @date 2018/1/28
 */
public final class RouterDetailVO {

    private int id;
    
    private String name;
    
    private String code;
    
    private String url;
    
    private int parentId;
    
    private String parentName;
    
    private short level;
    
    private boolean locked;
    
    private int displayOrder;
    
    private String properties;
    
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
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
    
    public int getParentId() {
        return this.parentId;
    }
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    
    public String getParentName() {
        return this.parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    
    public short getLevel() {
        return this.level;
    }
    public void setLevel(short level) {
        this.level = level;
    }
    
    public boolean getLocked() {
        return this.locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    public int getDisplayOrder() {
        return this.displayOrder;
    }
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
    
    public String getProperties() {
        return this.properties;
    }
    public void setProperties(String properties) {
        this.properties = properties;
    }

}
