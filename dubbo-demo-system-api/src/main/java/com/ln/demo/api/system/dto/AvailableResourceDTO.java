package com.ln.demo.api.system.dto;

/**
 * 可用资源dto类（Shiro动态FilterChainDefinitions dto类）
 * 
 * @author Lining
 * @date 2018/2/11
 */
public final class AvailableResourceDTO implements java.io.Serializable {

    private static final long serialVersionUID = -5316506151248386322L;

    /*
     * 该类的数据来自于Resource（sys_resource）
     */
    
    private int id;

    private String name;

    private String code;

    private String url;
    
    private int routerId;
    
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
    
    public int getRouterId() {
        return this.routerId;
    }
    public void setRouterId(int routerId) {
        this.routerId = routerId;
    }
}
