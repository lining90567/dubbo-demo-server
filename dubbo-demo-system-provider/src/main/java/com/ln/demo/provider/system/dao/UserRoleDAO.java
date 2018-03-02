package com.ln.demo.provider.system.dao;

import java.util.List;

import com.ln.demo.api.system.entity.Role;

/**
 * 用户角色dao接口
 * 
 * @author Lining
 * @date 2018/2/9
 */
public interface UserRoleDAO {
    
    /**
     * 根据用户id得到角色列表
     * @param userId
     * @return
     */
    List<Role> listUserRole(int userId);
    
    /**
     * 添加用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    int saveUserRole(int userId, int[] roleIds);
    
    /**
     * 根据用户id删除用户角色
     * @param userId
     * @return
     */
    int removeByUserId(int userId);
    
    /**
     * 根据角色id删除用户角色
     * @param roleId
     * @return
     */
    int removeByRoleId(int roleId);

}
