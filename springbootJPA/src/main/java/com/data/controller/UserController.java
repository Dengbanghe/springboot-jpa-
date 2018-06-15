package com.data.controller;

import java.util.List;

import com.data.annotation.AuthorityManagement;
import com.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.data.entity.User;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author DengBangHe
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService us;
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getUsers")
	@AuthorityManagement({"3301"})
	public String getUsers(Model model){
		List<User> list = us.selectAllUser();
		model.addAttribute("userList", list);
		return "index";
	}

	@RequestMapping(value = "getUser")
	@ResponseBody
	public Object getUser(){
		List<User> list = us.selectAllUser();
		return  list;
	}


}
