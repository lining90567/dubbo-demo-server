package com.ln.demo.vo;

/**
 * 路由导航VO类
 * 
 * @author Lining
 * @date 2017/12/19
 */
public final class RouterNavVO {

    private int id;
    
    private String code;
    
    private String name;
    
    private String url;
    
    private String properties;
    
    private int parentId;
    
    private short level;
    
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
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
    
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getProperties() {
        return this.properties;
    }
    public void setProperties(String properties) {
        this.properties = properties;
    }
    
    public int getParentId() {
        return this.parentId;
    }
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    
    public short getLevel() {
        return this.level;
    }
    public void setLevel(short level) {
        this.level = level;
    }
    
}
