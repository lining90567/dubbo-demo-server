package com.ln.demo.vo;

/**
 * 路由列表vo类
 * 
 * @author Lining
 * @date 2018/1/26
 */
public final class RouterListVO {
    
    public int id;
    
    public String name;
    
    private String code;
    
    private String url;
    
    private short level;
    
    private boolean locked;
    
    private int displayOrder;
    
    private int parentId;
    
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
    
    public int getParentId() {
        return this.parentId;
    }
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

}
