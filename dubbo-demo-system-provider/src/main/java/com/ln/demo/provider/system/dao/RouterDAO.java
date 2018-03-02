package com.ln.demo.provider.system.dao;

import java.util.List;
import java.util.Map;

import com.ln.demo.api.system.entity.Router;
import com.ln.demo.common.data.PageRequest;

/**
 * 前端路由DAO类
 * 
 * @author Lining
 * @date 2017/12/19
 */
public interface RouterDAO {
    
    /**
     * 统计路由数
     * @param parameters
     * @return
     */
    int countRouter(Map<String, Object> parameters);

    /**
     * 得到路由列表
     * @param pageRequest
     * @return
     */
    List<Router> listRouter(PageRequest pageRequest);
    
    /**
     * 根据id得到路由信息
     * @param id
     * @return
     */
    Router getById(int id);
    
    /**
     * 根据上级路由id得到路由列表
     * @param parentId
     * @return
     */
    List<Router> listByParentId(int parentId);
    
    /**
     * 添加路由
     * @param router
     */
    void saveRouter(Router router);
    
    /**
     * 更新路由
     * @param router
     * @return
     */
    int updateRouter(Router router);
    
    /**
     * 删除路由
     * @param id
     * @return
     */
    int removeRouter(int id);
    
    /**
     * 得到所有路由
     * @param includeLocked
     * @return
     */
    List<Router> listAll(boolean includeLocked);
    
}
