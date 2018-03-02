package com.ln.demo.api.system.dto;

/**
 * 资源dto类
 * 
 * @author Lining
 * @date 2018/2/11
 */
public final class ResourceDTO implements java.io.Serializable {

    private static final long serialVersionUID = 4368450080812978404L;

    private int id;

    private String name;

    private String code;

    private String url;

    private String permission;

    private boolean locked;

    private int routerId;
    
    private String routerName;

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

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean getLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getRouterId() {
        return this.routerId;
    }

    public void setRouterId(int routerId) {
        this.routerId = routerId;
    }
    
    public String getRouterName() {
        return this.routerName;
    }
    
    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

}
