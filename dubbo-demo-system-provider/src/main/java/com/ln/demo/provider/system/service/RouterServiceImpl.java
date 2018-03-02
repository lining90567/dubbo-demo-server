package com.ln.demo.provider.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ln.demo.api.system.dto.RouterDTO;
import com.ln.demo.api.system.entity.Router;
import com.ln.demo.api.system.service.RouterService;
import com.ln.demo.common.data.Page;
import com.ln.demo.common.data.PageRequest;
import com.ln.demo.provider.system.dao.RouterDAO;
import com.ln.demo.provider.system.dao.RouterPermissionDAO;

/**
 * 前端路由服务实现类
 * 
 * @author Lining
 * @date 2017/12/19
 */
@Service("routerService")
public class RouterServiceImpl implements RouterService {
    
    @Autowired
    private RouterDAO routerDAO;
    
    @Autowired
    private RouterPermissionDAO routerPermissionDAO;
    
    /**
     * 得到路由列表
     * @param parameters
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public Page<RouterDTO> listRouter(Map<String, Object> parameters, int offset, int limit) {
        int total = routerDAO.countRouter(parameters);
        List<Router> routerList = null;
        List<RouterDTO> dtoList = null;
        if(total > 0) {
            PageRequest pageRequest = new PageRequest(offset, limit, parameters, null);
            routerList = routerDAO.listRouter(pageRequest);
            
            dtoList = new ArrayList<RouterDTO>(routerList.size());
            for(Router router : routerList) {
                RouterDTO dto = new RouterDTO();
                BeanUtils.copyProperties(router, dto);
                if(router.getParent() != null) {
                    dto.setParentId(router.getParent().getId());
                    dto.setParentName(router.getParent().getName());
                }
                dtoList.add(dto);
            }
        }
        return new Page<RouterDTO>(total, dtoList);
    }

    /**
     * 得到已授权的路由
     * 
     * @param userId
     *            用户id
     * @return
     */
    @Override
    public List<RouterDTO> listAuthorizedRouter(int userId) {
        List<Router> routerList = this.routerPermissionDAO.listByUserId(userId);
        List<RouterDTO> dtoList = new ArrayList<RouterDTO>(routerList.size());
        for(Router router : routerList) {
            RouterDTO dto = new RouterDTO();
            BeanUtils.copyProperties(router, dto);
            if(router.getParent() != null) {
                dto.setParentId(router.getParent().getId());
                dto.setParentName(router.getParent().getName());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
    
    /**
     * 根绝id得到路由信息
     * @param id
     * @return
     */
    @Override
    public RouterDTO getById(int id) {
        Router router = this.routerDAO.getById(id);
        RouterDTO dto = new RouterDTO();
        BeanUtils.copyProperties(router, dto);
        if(router.getParent() != null) {
            dto.setParentId(router.getParent().getId());
            dto.setParentName(router.getParent().getName());
        }
        return dto;
    }
    
    /**
     * 根据上级路由id得到路由列表
     * @param parentId
     * @return
     */
    @Override
    public List<RouterDTO> listByParentId(int parentId) {
        List<Router> routerList = this.routerDAO.listByParentId(parentId);
        List<RouterDTO> dtoList = new ArrayList<RouterDTO>(routerList.size());
        for(Router router : routerList) {
            RouterDTO dto = new RouterDTO();
            BeanUtils.copyProperties(router, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }
    
    /**
     * 更新路由
     * @param routerDto
     * @return
     */
    @Override
    @Transactional
    public int updateRouter(RouterDTO routerDto) {
        Router router = new Router();
        BeanUtils.copyProperties(routerDto, router);
        Router parent = new Router();
        parent.setId(routerDto.getParentId());
        router.setParent(parent);
        return this.routerDAO.updateRouter(router);
    }
    
    /**
     * 添加路由
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public RouterDTO saveRouter(RouterDTO dto) {
        Router router = new Router();
        BeanUtils.copyProperties(dto, router);
        Router parent = new Router();
        parent.setId(dto.getParentId());
        router.setParent(parent);
        this.routerDAO.saveRouter(router);
        dto.setId(router.getId());
        return dto;
    }
    
    /**
     * 删除路由
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int removeRouter(int id) {
        return this.routerDAO.removeRouter(id);
    }
    
    /**
     * 得到所有路由
     * @param includeLocked 包括状态为已禁用的
     * @return
     */
    @Override
    public List<RouterDTO> listAll(boolean includeLocked) {
        List<Router> list = this.routerDAO.listAll(includeLocked);
        List<RouterDTO> dtoList = new ArrayList<RouterDTO>(list.size());
        for(Router router : list) {
            RouterDTO dto = new RouterDTO();
            BeanUtils.copyProperties(router, dto);
            dto.setParentId(router.getParent() != null ? router.getParent().getId() : 0);
            dtoList.add(dto);
        }
        return dtoList;
    }

}
