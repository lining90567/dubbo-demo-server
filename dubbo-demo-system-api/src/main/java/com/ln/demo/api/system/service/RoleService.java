package com.ln.demo.api.system.service;

import java.util.List;
import java.util.Map;

import com.ln.demo.api.system.dto.RoleDTO;
import com.ln.demo.common.data.Page;

/**
 * 角色服务接口
 * 
 * @author Lining
 * @date 2017/10/27
 */
public interface RoleService {

	/**
	 * 得到角色列表
	 * @param paremeters
	 * @param offset
	 * @param limit
	 * @return
	 */
	Page<RoleDTO> listRole(Map<String, Object> paremeters, int offset, int limit);
	
	/**
	 * 添加角色
	 * @param dto
	 * @return
	 */
	RoleDTO saveRole(RoleDTO dto);
	
	/**
	 * 根据id得到角色
	 * @param id
	 * @return
	 */
	RoleDTO getById(int id);
	
	/**
	 * 更新角色
	 * @param dto
	 * @return
	 */
	int updateRole(RoleDTO dto);
	
	/**
	 * 得到所有角色
	 * @return
	 */
	List<RoleDTO> listAllRole();
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	int removeRole(int id);
    
    /**
     * 得到资源权限列表
     * @param roleId
     * @return
     */
    List<Integer> listResourcePermission(int roleId);
    
    /**
     * 保存角色权限
     * @param roleId
     * @param routerIds
     * @param resourceIds
     * @return
     */
    int savePermission(int roleId, int[] routerIds, int[] resourceIds);

}
