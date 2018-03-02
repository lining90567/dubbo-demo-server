package com.ln.demo.api.system.service;

import java.util.List;
import java.util.Map;

import com.ln.demo.api.system.dto.RouterDTO;
import com.ln.demo.common.data.Page;

/**
 * 路由服务接口
 * 
 * @author Lining
 * @date 2017/12/19
 */
public interface RouterService {


    /**
     * 得到路由列表
     * @param parameters
     * @param offset
     * @param limit
     * @return
     */
    Page<RouterDTO> listRouter(Map<String, Object> parameters, int offset, int limit);

    /**
     * 得到已授权的路由
     * 
     * @param userId
     *            用户id
     * @return
     */
    List<RouterDTO> listAuthorizedRouter(int userId);
    
    /**
     * 根据路由id得到路由信息
     * @param id
     * @return
     */
    RouterDTO getById(int id);
    
    /**
     * 根据上级路由id得到路由列表
     * @param parentId
     * @return
     */
    List<RouterDTO> listByParentId(int parentId);
    
    /**
     * 更新路由
     * @param routerDto
     * @return
     */
    int updateRouter(RouterDTO routerDto);
    
    /**
     * 添加路由
     * @param dto
     * @return
     */
    RouterDTO saveRouter(RouterDTO dto);
    
    /**
     * 删除路由
     * @param id
     * @return
     */
    int removeRouter(int id);
    
    /**
     * 得到所有路由
     * @param includeLocked 包括状态为已禁用的
     * @return
     */
    List<RouterDTO> listAll(boolean includeLocked);

}
