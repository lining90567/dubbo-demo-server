package com.ln.demo.shiro;

import java.text.MessageFormat;
import java.util.List;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.ln.demo.api.system.dto.AvailableResourceDTO;
import com.ln.demo.api.system.service.ResourceService;

/**
 * Shiro动态FilterChainDefinitions
 * 
 * @author Lining
 * @date 2017/12/18
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

	@Autowired
	private ResourceService resourceService;

	/**
	 * 默认url过滤定义（shiro过滤器的filterChainDefinitions属性）
	 */
	private String filterChainDefinitions;

	/**
	 * 设置默认url过滤定义
	 * 
	 * @param filterChainDefinitions
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Section getObject() throws Exception {
		/*******************************************
		 * rest：例子/admins/user/**=rest[user]，根据请求的方法，相当于/admins/user/**=perms[user:method]
		 * ,其中method为post，get，delete等。
		 * port：例子/admins/user/**=port[8081]，当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString，
		 * 其中schmal是协议http或https等，serverName是你访问的host，8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
		 * perms：例子/admins/user/**=perms[user:add:*]，perms参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，
		 * 例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
		 * roles：例子/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，
		 * 例如/admins/user/**=roles["admin,guest"]，每个参数通过才算通过，相当于hasAllRoles()方法。
		 * anon：例子/admins/**=anon 没有参数，表示可以匿名使用。
		 * authc：例如/admins/user/**=authc表示需要认证才能使用，没有参数。
		 * authcBasic：例如/admins/user/**=authcBasic没有参数表示httpBasic认证。
		 * ssl：例子/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
		 * user：例如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查。
		 *******************************************/
	    //jwtAuthcFilter控制器用来做身份认证，用restfulPermissionFilter做restful权限验证。
		String restPermissionString = "jwtAuthcFilter,restfulPermissionFilter[{0}]";
		
		// 加载默认的url过滤定义
		Ini ini = new Ini();
		ini.load(this.filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);

		List<AvailableResourceDTO> dtoList = this.resourceService.listAllAvailable();
		// 将自定义url过滤添加到section中
		for (AvailableResourceDTO dto : dtoList) {
            if(dto.getCode() != null && !dto.getCode().trim().equals("")) {
                if(!section.containsKey(dto.getCode())) {
                    section.put(dto.getUrl(), MessageFormat.format(restPermissionString, dto.getCode()));
                }
            }
		}
		section.put("/**", "jwtAuthcFilter");
		return section;
	}

	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

}
