package com.ln.demo.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

public class JwtAuthcFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        //处理跨域请求
        if(request instanceof ShiroHttpServletRequest) {
            if(StringUtils.equalsIgnoreCase("OPTIONS", ((ShiroHttpServletRequest) request).getMethod())) {
                return true;
            }
        }
        
        // 拦截后先进入该方法。直接返回false，交由onAccessDenied处理鉴权与登录逻辑
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //从header中得到token
        String token = ((HttpServletRequest)request).getHeader("X-Token");
        String host = request.getRemoteHost();

        JwtToken jwtToken = new JwtToken(token, host);
        try {
            //委托给Realm进行登录
            getSubject(request, response).login(jwtToken);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
