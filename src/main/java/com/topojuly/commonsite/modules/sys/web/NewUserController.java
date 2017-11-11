/**
 * Copyright &copy; 2017 <a href="https://github.com/topojuly/commonsite">topojuly</a> All rights reserved.
 */
package com.topojuly.commonsite.modules.sys.web;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.topojuly.commonsite.common.config.Global;
import com.topojuly.commonsite.common.utils.DateUtils;
import com.topojuly.commonsite.common.utils.StringUtils;
import com.topojuly.commonsite.common.web.BaseController;
import com.topojuly.commonsite.modules.sys.entity.Office;
import com.topojuly.commonsite.modules.sys.entity.Role;
import com.topojuly.commonsite.modules.sys.entity.User;
import com.topojuly.commonsite.modules.sys.service.SystemService;
import com.topojuly.commonsite.modules.sys.utils.UserUtils;

/**
 * 用户Controller
 * @author topojuly
 * @version 2017-11-10
 */
@Controller
@RequestMapping(value = "${frontPath}/user")
public class NewUserController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getUser(id);
		}else{
			return new User();
		}
	}
	
	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String loginName) {
		if (!loginName.isEmpty() && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}
    
	@RequestMapping(value = "add")
	public String newuser() {
		return "topojuly/newuser";
	}
	
	@RequestMapping(value = "save")
	public String newsave(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:/user/add";
		}
		user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));
		user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
		if (!beanValidator(model, user)){
			return "topojuly/newuser";
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = Lists.newArrayList();
		roleIdList.add("6");
		for (Role r : systemService.findAllRole()){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		Office office = new Office();
		office.setId("0");
		office.setCode("0");
		user.setRoleList(roleList);
		user.setCompany(office);
		user.setOffice(office);
		user.setCreateBy(new User(""));
		user.setCreateDate(Date.valueOf(DateUtils.getDate()));
		user.setUpdateBy(new User(""));
		user.setUpdateDate(Date.valueOf(DateUtils.getDate()));
		user.setDelFlag("0");
		user.setRemarks("互联网注册用户");
		
		// 保存用户信息
		systemService.saveUser(user);
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
			UserUtils.clearCache();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
}
