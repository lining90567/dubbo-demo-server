package com.ln.demo.api.system.entity;

/**
 * 前端路由实体类
 * 
 * @author Lining
 * @date 2017/12/19
 */
public class Router implements java.io.Serializable {
    
    private static final long serialVersionUID = 7627998429341237040L;

    private int id;
    
    private String name;
    
    private String code;
    
    private String url;
    
    private Router parent;
    
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
    
    public Router getParent() {
        return this.parent;
    }
    public void setParent(Router parent) {
        this.parent = parent;
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
