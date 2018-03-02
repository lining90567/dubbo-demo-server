package com.ln.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ln.demo.api.system.dto.ResourceDTO;
import com.ln.demo.api.system.service.ResourceService;
import com.ln.demo.util.JwtUtils;

@Controller
public class HomeController extends BaseController {
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping("/index")
	public ModelAndView index(@CookieValue(value="jwt") String jwt) {
		//得到用户id
		String currentUserId = this.getSubjectFromJwt(jwtUtils, jwt, "userId");
		// 得到所有已授权的菜单
		List<ResourceDTO> list = this.resourceService.listAuthorizedByUserId(Integer.parseInt(currentUserId));
		ModelAndView result = new ModelAndView("index");
		result.addObject("menuList", list);
		return result;
	}

}
