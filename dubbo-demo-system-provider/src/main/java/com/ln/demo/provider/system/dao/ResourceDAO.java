package com.ln.demo.provider.system.dao;

import java.util.List;
import java.util.Map;

import com.ln.demo.api.system.entity.Resource;

/**
 * 资源管理DAO接口
 * 
 * @author Lining
 * @date 2017/10/25
 */
public interface ResourceDAO {

	/**
	 * 得到资源列表
	 * 
	 * @param params
	 *            包含查询参数的Map
	 * @return
	 */
	List<Resource> listResource(Map<String, Object> params);

	/**
	 * 得到用户授权的资源
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 */
	List<Resource> listAuthorized(int userId);
	
	/**
	 * 添加资源
	 * @param resource
	 * @return
	 */
	int saveResource(Resource resource);
	
	/**
	 * 修改资源
	 * @param resource
	 * @return
	 */
	int updateResource(Resource resource);
	
	/**
	 * 删除资源
	 * @param id
	 * @return
	 */
	int removeResource(int id);
	
	/**
	 * 根据id得到资源
	 * @param id
	 * @return
	 */
	Resource getById(int id);
	
	/**
	 * 根据路由id得到资源列表
	 * @param routerId
	 * @return
	 */
	List<Resource> listByRouterId(int routerId);

}
