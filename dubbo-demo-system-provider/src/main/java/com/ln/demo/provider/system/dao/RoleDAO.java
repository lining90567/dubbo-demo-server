package com.ln.demo.provider.system.dao;

import java.util.List;
import java.util.Map;

import com.ln.demo.api.system.entity.Role;
import com.ln.demo.common.data.PageRequest;

/**
 * 角色DAO接口
 * 
 * @author Lining
 * @date 2017/10/27
 */
public interface RoleDAO {

    /**
     * 统计角色数
     * 
     * @param condition
     *            查询条件
     * @return
     */
    int countRole(Map<String, Object> parameters);

    /**
     * 分页查询角色信息
     * 
     * @param pageRequest
     *            分页请求参数
     * @return
     */
    List<Role> listRole(PageRequest pageRequest);
    
    /**
     * 添加角色
     * @param role
     */
    void saveRole(Role role);
    
    /**
     * 根据id得到角色
     * @param id
     * @return
     */
    Role getById(int id);
    
    /**
     * 更新角色
     * @param role
     * @return
     */
    int updateRole(Role role);
    
    /**
     * 删除角色
     * @param id
     * @return
     */
    int removeRole(int id);
    
    /**
     * 得到所有角色
     * @return
     */
    List<Role> listAllRole();

}
