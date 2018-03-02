package com.ln.demo.vo;

/**
 * 资源权限vo类
 * 
 * @author Lining
 * @date 2018/2/26
 */
public final class ResourcePermissionVO {
    
    private int roleId;
    
    private int resourceId;
    
    private String resourcePermission;
    
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
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
