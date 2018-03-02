package com.ln.demo.provider.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ln.demo.api.system.dto.RoleDTO;
import com.ln.demo.api.system.entity.Role;
import com.ln.demo.api.system.service.RoleService;
import com.ln.demo.common.data.Page;
import com.ln.demo.common.data.PageRequest;
import com.ln.demo.provider.system.dao.ResourcePermissionDAO;
import com.ln.demo.provider.system.dao.RoleDAO;
import com.ln.demo.provider.system.dao.RouterPermissionDAO;
import com.ln.demo.provider.system.dao.UserRoleDAO;

/**
 * 角色服务实现类
 * 
 * @author Lining
 * @date 2017/10/27
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private UserRoleDAO userRoleDAO;
	
	@Autowired
	private ResourcePermissionDAO resourcePermissionDAO;
	
	@Autowired
	private RouterPermissionDAO routerPermissionDAO;

    /**
     * 得到角色列表
     * @param parameters
     * @param offset
     * @param limit
     * @return
     */
	@Override
    public Page<RoleDTO> listRole(Map<String, Object> parameters, int offset, int limit) {
        int total = this.roleDAO.countRole(parameters);
        List<RoleDTO> dtoList = null;
        if(total > 0) {
            PageRequest pageRequest = new PageRequest(offset, limit, parameters, null);
            List<Role> list = this.roleDAO.listRole(pageRequest);
            dtoList = new ArrayList<RoleDTO>(list.size());
            for(Role role : list) {
                RoleDTO dto = new RoleDTO();
                BeanUtils.copyProperties(role, dto);
                dtoList.add(dto);
            }
        }
        return new Page<RoleDTO>(total, dtoList);
    }
    
	/**
	 * 添加角色
	 * @param dto
	 * @return
	 */
    @Override
    @Transactional
    public RoleDTO saveRole(RoleDTO dto) {
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        this.roleDAO.saveRole(role);
        dto.setId(role.getId());
        return dto;
    }
    
    /**
     * 根据id得到角色
     * @param id
     * @return
     */
    @Override
    public RoleDTO getById(int id) {
        Role role = this.roleDAO.getById(id);
        if(role == null) {
            return null;
        }
        RoleDTO dto = new RoleDTO();
        BeanUtils.copyProperties(role, dto);
        return dto;
    }
    
    /**
     * 更新角色
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public int updateRole(RoleDTO dto) {
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        return this.roleDAO.updateRole(role);
    }
    
    /**
     * 得到所有角色
     * @return
     */
    @Override
    public List<RoleDTO> listAllRole() {
        List<Role> list = this.roleDAO.listAllRole();
        List<RoleDTO> dtoList = new ArrayList<RoleDTO>(list.size());
        for(Role role : list) {
            RoleDTO dto = new RoleDTO();
            BeanUtils.copyProperties(role, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }
    
    /**
     * 删除角色
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int removeRole(int id) {
        int rows = this.roleDAO.removeRole(id);
        this.userRoleDAO.removeByRoleId(id);
        this.resourcePermissionDAO.removeByRoleId(id);
        this.routerPermissionDAO.removeByRoleId(id);
        return rows;
    }
    
    /**
     * 得到资源权限列表
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> listResourcePermission(int roleId) {
        return this.resourcePermissionDAO.listByRoleId(roleId);
    }

    /**
     * 保存角色权限
     * @param roleId
     * @param routerIds
     * @param resourceIds
     * @return
     */
    @Override
    @Transactional
    public int savePermission(int roleId, int[] routerIds, int[] resourceIds) {
        // 先删除后添加
        this.routerPermissionDAO.removeByRoleId(roleId);
        this.resourcePermissionDAO.removeByRoleId(roleId);
        
        int rows = 0;
        rows += this.routerPermissionDAO.savePermission(roleId, routerIds);
        rows += this.resourcePermissionDAO.savePermission(roleId, resourceIds);
        return rows;
    }
    
}
