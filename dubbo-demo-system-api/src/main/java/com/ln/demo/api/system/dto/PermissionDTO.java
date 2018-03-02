package com.ln.demo.api.system.dto;

/**
 * 权限dto类
 * 
 * @author Lining
 * @date 2018/2/27
 */
public final class PermissionDTO implements java.io.Serializable {

    private static final long serialVersionUID = 1318329930069664403L;

    private int[] routerIds;
    
    private int[] resourceIds;
    
    public int[] getRouterIds() {
        return this.routerIds;
    }
    public void setRouterIds(int[] routerIds) {
        this.routerIds = routerIds;
    }
    
    public int[] getResourceIds() {
        return this.resourceIds;
    }
    public void setResourceIds(int[] resourceIds) {
        this.resourceIds = resourceIds;
    }
    
}
