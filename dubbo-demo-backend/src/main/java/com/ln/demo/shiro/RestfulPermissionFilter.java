package com.ln.demo.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

/**
 * RESTful权限过滤器
 * 
 * @author Lining
 * @date 2018/2/6
 */
public class RestfulPermissionFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        String method = ((HttpServletRequest) request).getMethod();

        // 处理跨域请求
        if (request instanceof ShiroHttpServletRequest) {
            if (StringUtils.equalsIgnoreCase("OPTIONS", method)) {
                return true;
            }
        }

        String permissionString = ((String[]) mappedValue)[0];
        Subject subject = getSubject(request, response);
        switch (method.toLowerCase()) {
        case "get":
            permissionString += ":read";
            break;
        case "put":
            permissionString += ":update";
            break;
        case "post":
            permissionString += ":create";
            break;
        case "delete":
            permissionString += ":delete";
            break;
        }
        return subject.isPermitted(permissionString);
    }

}
