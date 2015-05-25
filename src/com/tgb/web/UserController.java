package com.tgb.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tgb.entity.User;
import com.tgb.manager.UserManager;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserManager userManager;
    
	@RequestMapping("/getAllUser")
	public String getAllUser(HttpServletRequest request){
		System.out.println(userManager);
		request.setAttribute("userList", userManager.getAllUser());
		
		return "/index";
	}
	
	@RequestMapping("/getUser")
	public String getUser(String id,HttpServletRequest request){
		System.out.println(userManager);
		request.setAttribute("user", userManager.getUser(id));
	
		return "/editUser";
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser(){
		return "/addUser";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user,HttpServletRequest request){
		
		userManager.addUser(user);
		
		return "redirect:/user/getAllUser";
	}
	
	@RequestMapping("/delUser")
	public void delUser(String id,HttpServletResponse response){
		System.out.println(userManager);
		String result = "{\"result\":\"error\"}";
		
		if(userManager.delUser(id)){
			result = "{\"result\":\"success\"}";
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(User user,HttpServletRequest request){
		System.out.println(userManager);
		if(userManager.updateUser(user)){
			user = userManager.getUser(user.getId());
			request.setAttribute("user", user);
			return "redirect:/user/getAllUser";
		}else{
			return "/error";
		}
	}
}