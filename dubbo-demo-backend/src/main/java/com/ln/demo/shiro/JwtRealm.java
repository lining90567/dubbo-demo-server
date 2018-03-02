package com.ln.demo.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.ln.demo.api.system.service.ResourceService;
import com.ln.demo.util.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * 无状态Shiro Realm
 * 
 * @author Lining
 * @date 2017/12/15
 */
public class JwtRealm extends AuthorizingRealm {
    
    @Autowired
    private ResourceService resourceService;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*********************************************
         * RestfulPermissionFilter 过滤器说明：
         * Actions representing HTTP Method values (GET -> read, POST -> create, etc)
         * private static final String CREATE_ACTION = "create";
         * private static final String READ_ACTION = "read";
         * private static final String UPDATE_ACTION = "update";
         * private static final String DELETE_ACTION = "delete";
         *********************************************/
        // 如果不为securityManager配置缓存管理器，该方法在每次鉴权前都会从数据库中查询权限数据。
        // 分布式环境下，建议将权限保存在redis中，避免每次从数据库中加载。
        //JSONObject json = JSONObject.parseObject(principals.toString());
        Claims claims = jwtUtils.parseJWT(principals.toString());
        JSONObject json = JSONObject.parseObject(claims.getSubject());
        // 得到用户的权限code
        List<String> permissionCodeList = resourceService.listPermissionCodeByUserId(json.getIntValue("userId"));
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        for (String permissionCode : permissionCodeList) {
            if (permissionCode != null && !permissionCode.trim().equals("")) {
                simpleAuthorInfo.addStringPermission(permissionCode);
            }
            // 如果要添加基于角色的鉴权，可调用simpleAuthorInfo.addRole("role_name")添加用户所属角色。
        }
        return simpleAuthorInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String jwt = (String) jwtToken.getPrincipal();
        try {
            Claims claims = jwtUtils.parseJWT(jwt);
            //验证host
            JSONObject json = JSONObject.parseObject(claims.getSubject());
            if(!jwtToken.getHost().equals(json.getString("host"))) {
                throw new AuthenticationException("令牌来路非法");
            }
            return new SimpleAuthenticationInfo(jwt, Boolean.TRUE, json.getString("username"));
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("令牌过期:" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("令牌无效:" + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("令牌格式错误:" + e.getMessage());
        } catch (SignatureException e) {
            throw new AuthenticationException("令牌签名无效:" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("令牌参数异常:" + e.getMessage());
        } catch (Exception e) {
            throw new AuthenticationException("令牌错误:" + e.getMessage());
        }
    }

}
