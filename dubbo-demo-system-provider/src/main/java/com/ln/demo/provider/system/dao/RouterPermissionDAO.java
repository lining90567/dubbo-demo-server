package com.ln.demo.provider.system.dao;

import java.util.List;

import com.ln.demo.api.system.entity.Router;

/**
 * 路由权限DAO接口
 * 
 * @author Lining
 * @date 2017/12/22
 */
public interface RouterPermissionDAO {

    /**
     * 根据用户id得到路由权限列表
     * @param userId
     * @return
     */
    List<Router> listByUserId(int userId);
    
    /**
     * 根据角色id删除路由权限
     * @param roleId
     */
    void removeByRoleId(int roleId);
    
    /**
     * 保存路由权限
     * @param roleId
     * @param routerIds
     * @return
     */
    int savePermission(int roleId, int[] routerIds);
    
}
