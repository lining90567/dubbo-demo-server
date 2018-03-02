package com.ln.demo.api.system.dto;

/**
 * 资源权限dto类
 * 
 * @author Lining
 * @date 2018/2/26
 */
public final class ResourcePermissionDTO implements java.io.Serializable {

    private static final long serialVersionUID = -8134140320624062359L;

    private int resourceId;

    private String resourcePermission;

    public int getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourcePermission() {
        return this.resourcePermission;
    }

    public void setResourcePermission(String resourcePermission) {
        this.resourcePermission = resourcePermission;
    }

}
