package com.HFTest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.HFTest.entities.Role;
import com.HFTest.entities.Shops;
import com.HFTest.entities.User;

public interface UserService extends UserDetailsService {

	public User addUser(User user);
	public User getUserbyId(String userId);
	public User getUser(String email);
	public void updateUser(String Id, User user);
	public boolean deleteUser(String userId);
	public List<User> getUsers();
	public boolean assignRoleToUser(Role role, User user);
	public User assignShopToUser(Shops shop, User user);
	public User removeShopFromUser(Shops shop, User user);
	
}
