package com.ln.demo.provider.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ln.demo.api.system.dto.AvailableResourceDTO;
import com.ln.demo.api.system.dto.ResourceDTO;
import com.ln.demo.api.system.dto.ResourcePermissionDTO;
import com.ln.demo.api.system.entity.Resource;
import com.ln.demo.api.system.entity.Router;
import com.ln.demo.api.system.service.ResourceService;
import com.ln.demo.provider.system.dao.ResourceDAO;
import com.ln.demo.provider.system.dao.ResourcePermissionDAO;

/**
 * 资源服务实现类
 * 
 * @author Lining
 * @date 2017/10/26
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDAO resourceDAO;
    
    @Autowired
    private ResourcePermissionDAO permissionDAO;

    /**
     * 得到所有可用的资源
     * 
     * @return
     */
    @Override
    public List<AvailableResourceDTO> listAllAvailable() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("locked", 0);
        List<Resource> list = this.resourceDAO.listResource(params);
        List<AvailableResourceDTO> dtoList = new ArrayList<AvailableResourceDTO>(list.size());
        for(Resource resource : list) {
            AvailableResourceDTO dto = new AvailableResourceDTO();
            BeanUtils.copyProperties(resource, dto);
            dto.setRouterId(resource.getRouter() != null ? resource.getRouter().getId() : 0);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * 得到用户授权的资源
     * 
     * @param userId
     *            用户Id
     * @return
     */
    @Override
    public List<ResourceDTO> listAuthorizedByUserId(int userId) {
        List<Resource> list = this.resourceDAO.listAuthorized(userId);
        return getDtoList(list);
    }
    
    /**
     * 根据路由id得到资源列表
     * @param routerId
     * @return
     */
    @Override
    public List<ResourceDTO> listByRouterId(int routerId) {
        List<Resource> list = this.resourceDAO.listByRouterId(routerId);
        return getDtoList(list);
    }
    
    /**
     * 根据id得到资源
     * @param id
     * @return
     */
    @Override
    public ResourceDTO getById(int id) {
        Resource resource = this.resourceDAO.getById(id);
        if(resource == null) {
            return null;
        }
        ResourceDTO dto = new ResourceDTO();
        BeanUtils.copyProperties(resource, dto);
        dto.setRouterId(resource.getRouter().getId());
        dto.setRouterName(resource.getRouter().getName());
        return dto;
    }
    
    /**
     * 添加资源
     * @param dto
     * @return 新增资源的主键值
     */
    @Override
    @Transactional
    public int saveResource(ResourceDTO dto) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(dto, resource);
        Router router = new Router();
        router.setId(dto.getRouterId());
        resource.setRouter(router);
        return this.resourceDAO.saveResource(resource);
    }
    
    /**
     * 更新资源
     * @param dto
     * @return 数据库中受影响的行数
     */
    @Override
    @Transactional
    public int updateResource(ResourceDTO dto) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(dto, resource);
        Router router = new Router();
        router.setId(dto.getRouterId());
        resource.setRouter(router);
        return this.resourceDAO.updateResource(resource);
    }
    
    /**
     * 删除资源
     * @param id
     * @return 数据库中受影响的行数
     */
    @Override
    @Transactional
    public int removeResource(int id) {
        return this.resourceDAO.removeResource(id);
    }
    
    /**
     * 得到用户资源权限
     * 
     * @param userId
     *            用户Id
     * @return
     */
    @Override
    public List<Resource> listPermissionByUserId(int userId) {
        return this.permissionDAO.listByUserId(userId);
    }
    
    /**
     * 得到资源权限
     * @param userId
     * @param routerId
     * @return
     */
    @Override
    public List<ResourcePermissionDTO> listPermission(int userId, int routerId) {
        List<Resource> resourceList = this.permissionDAO.listPermission(userId, routerId);
        List<ResourcePermissionDTO> dtoList = new ArrayList<ResourcePermissionDTO>(resourceList.size());
        for(Resource resource : resourceList) {
            ResourcePermissionDTO dto = new ResourcePermissionDTO();
            dto.setResourceId(resource.getId());
            dto.setResourcePermission(resource.getPermission());
            dtoList.add(dto);
        }
        return dtoList;
    }
    
    /**
     * 得到用户资源权限代码
     * 
     * @param userId
     *            用户Id
     * @return
     */
    @Override
    public List<String> listPermissionCodeByUserId(int userId) {
        return this.permissionDAO.listCodeByUserId(userId);
    }    
    
    /**
     * 得到dto列表
     * @param list
     * @return
     */
    private List<ResourceDTO> getDtoList(List<Resource> list) {
        List<ResourceDTO> dtoList = new ArrayList<ResourceDTO>(list.size());
        for(Resource resource : list) {
            ResourceDTO dto = new ResourceDTO();
            BeanUtils.copyProperties(resource, dto);
            dto.setRouterId(resource.getRouter().getId());
            dtoList.add(dto);
        }
        return dtoList;
    }

}
