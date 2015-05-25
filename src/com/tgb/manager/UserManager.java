package com.tgb.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tgb.entity.User;

public interface UserManager {

	public User getUser(String id);
	
	public List<User> getAllUser();
	
	public void addUser(User user);
	
	public boolean delUser(String id);
	
	public boolean updateUser(User user);
}
