package com.ln.demo.provider.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ln.demo.api.system.dto.UserDTO;
import com.ln.demo.api.system.entity.Role;
import com.ln.demo.api.system.entity.User;
import com.ln.demo.api.system.service.UserService;
import com.ln.demo.common.data.Page;
import com.ln.demo.common.data.PageRequest;
import com.ln.demo.common.exception.ServiceException;
import com.ln.demo.provider.system.dao.UserDAO;
import com.ln.demo.provider.system.dao.UserRoleDAO;

/**
 * 用户服务实现类
 * 
 * @author Lining
 * @date 2017/9/29
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private UserRoleDAO userRoleDAO;

    /**
     * 系统登录
     * 
     * @param loginName
     * @param password
     * @return true：成功；false：失败
     */
    @Override
    public UserDTO getByLoginName(String loginName) {
        User user = userDAO.getByLoginName(loginName);
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        if (user.getCreatedBy() != null) {
            dto.setCreatorName(user.getCreatedBy().getName());
        }
        return dto;
    }

    /**
     * 添加系统用户
     * 
     * @param dto
     *            UserDTO类的实例
     * @return
     */
    @Override
    @Transactional
    public UserDTO saveUser(UserDTO dto) {
        // 判断登录名是否重复
        User existsUser = userDAO.getByLoginName(dto.getLoginName());
        if(existsUser != null) {
            throw new ServiceException("登录名 " + dto.getLoginName() + " 已存在");
        }
        
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        User creator = new User();
        creator.setId(dto.getCreatorId());
        user.setCreatedBy(creator);
        user.setCreatedAt(new Date());
        userDAO.saveUser(user);
        
        // 保存用户角色
        userRoleDAO.removeByUserId(user.getId());
        userRoleDAO.saveUserRole(user.getId(), dto.getRoleIds());
        
        dto.setId(user.getId());
        return dto;
    }

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
    @Override
    @Transactional
    public int changePassword(int userId, String salt, String password) {
        return userDAO.updatePassword(userId, salt, password);
    }

    /**
     * 得到用户列表
     * 
     * @param parameters
     *            包含查询字段和value的map
     * @param order
     *            排序字符串
     * @param offset
     *            偏移量
     * @param limit
     *            查询数据条数
     * @return
     */
    @Override
    public Page<UserDTO> listUser(Map<String, Object> parameters, String order, int offset, int limit) {
        int total = userDAO.countUser(parameters);
        List<User> userList = null;
        List<UserDTO> dtoList = null;
        if (total > 0) {
            PageRequest pageRequest = new PageRequest(offset, limit, parameters, order);
            userList = userDAO.listUser(pageRequest);
            dtoList = new ArrayList<UserDTO>(userList.size());
            
            for(User user : userList) {
                UserDTO dto = new UserDTO();
                BeanUtils.copyProperties(user, dto);
                if (user.getCreatedBy() != null) {
                    dto.setCreatorName(user.getCreatedBy().getName());
                }
                dtoList.add(dto);
            }
        }
        return new Page<UserDTO>(total, dtoList);
    }

    /**
     * 根据Id查询用户
     * 
     * @param id
     *            用户id
     * @return
     */
    @Override
    public UserDTO getById(int id) {
        User user = userDAO.getById(id);
        if(user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        if (user.getCreatedBy() != null) {
            dto.setCreatorName(user.getCreatedBy().getName());
        }
        
        // 得到用户角色
        List<Role> roles = userRoleDAO.listUserRole(id);
        int[] roleIds = new int[roles.size()];
        for(int i = 0; i < roleIds.length; i++) {
            roleIds[i] = roles.get(i).getId();
        }
        dto.setRoleIds(roleIds);
        return dto;
    }

    /**
     * 更新用户信息
     * 
     * @param dto
     *            用户dto类
     * @return 更新影响的行数
     */
    @Override
    @Transactional
    public int updateUser(UserDTO dto) {
        // 判断登录名是否重复
        User existsUser = userDAO.getByLoginName(dto.getLoginName());
        if(existsUser != null && existsUser.getId() != dto.getId()) {
            throw new ServiceException("登录名" + dto.getLoginName() + " 已存在");
        }
        
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        int rows = userDAO.updateUser(user);
        // 保存用户角色
        if(rows > 0) {
            userRoleDAO.removeByUserId(dto.getId());
            if(dto.getRoleIds() != null && dto.getRoleIds().length > 0) {
                userRoleDAO.saveUserRole(dto.getId(), dto.getRoleIds());
            }
        }
        return rows;
    }

    /**
     * 删除用户
     * 
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int removeUser(int id) {
        int rows = userDAO.removeUser(id, new Date().getTime());
        // 删除用户角色
        if(rows > 0) {
            userRoleDAO.removeByUserId(id);
        }
        return rows;
    }
    
    /**
     * 更新当前用户信息
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public int updateCurrentUser(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setLoginName(dto.getLoginName());
        user.setName(dto.getName());
        int rows = userDAO.updateUser(user);
        if(rows > 0 && !StringUtils.isBlank(dto.getPassword())) {
            userDAO.updatePassword(dto.getId(), dto.getSalt(), dto.getPassword());
        }
        return rows;
    }

}
