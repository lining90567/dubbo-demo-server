package com.ln.demo.api.system.service;

import java.util.Map;

import com.ln.demo.api.system.dto.UserDTO;
import com.ln.demo.common.data.Page;

/**
 * 用户管理服务接口
 * 
 * @author Lining
 * @date 2017/9/28
 */
public interface UserService {

	/**
	 * 根据登录名称得到系统用户
	 * 
	 * @param loginName
	 *            登录名称
	 * @return
	 */
	UserDTO getByLoginName(String loginName);

	/**
	 * 添加系统用户
	 * 
	 * @param user
	 *            User类的实例
	 * @return
	 */
	UserDTO saveUser(UserDTO dto);

	/**
	 * 修改用户口令
	 * 
	 * @param userId
	 *            用户id
	 * @param salt
	 *            盐
	 * @param password
	 *            口令
	 * @return
	 */
	int changePassword(int userId, String salt, String password);
	
	/**
	 * 得到用户列表
	 * @param parameters 包含查询字段和value的map
	 * @param order 排序字符串
	 * @param offset 偏移量
	 * @param limit 查询数据条数
	 * @return
	 */
	Page<UserDTO> listUser(Map<String, Object> parameters, String order, int offset, int limit);
	
	/**
	 * 根据Id查询用户
	 * @param id 用户id
	 * @return
	 */
	UserDTO getById(int id);
	
	/**
	 * 更新用户信息
	 * @param dto 用户dto类
	 * @return 更新影响的行数
	 */
	int updateUser(UserDTO dto);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	int removeUser(int id);
	
    /**
     * 更新当前用户信息
     * @param dto
     * @return
     */
	int updateCurrentUser(UserDTO dto);

}
