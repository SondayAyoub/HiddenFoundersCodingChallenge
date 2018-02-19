package com.HFTest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.HFTest.entities.Shops;
import com.HFTest.entities.User;
import com.HFTest.service.UserService;

@RestController
@CrossOrigin("*")
public class UserRestService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public User sign(@RequestBody User user) {
		User u;
		u = (User) userService.loadUserByUsername(user.getEmail());
		String p = user.getPassword();

		if (passwordEncoder.matches(p, u.getPassword())) {
			return u;
		}
		else
			return null;
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public User register(@RequestBody User user) {
		return userService.addUser(user);
	}

	@RequestMapping(value="/user", method=RequestMethod.DELETE)
	public boolean deleteUser(String idUser) {
		return userService.deleteUser(idUser);
	}

	@RequestMapping(value="/user/{id}", method=RequestMethod.PUT)
	public void updateUser(@PathVariable String id, @RequestBody User user) {
	    userService.updateUser(id, user);
	}

	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable("id")String userId) {
		return userService.getUserbyId(userId);
	}

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getUsers();
	}
	
	@RequestMapping(value="/user/assignShop/{id}", method=RequestMethod.POST)
	public User assignShop(@RequestBody Shops shop, @PathVariable("id") String userId) {
		User user;
		user = userService.getUserbyId(userId);

		return userService.assignShopToUser(shop, user);
	}
	
	@RequestMapping(value="/user/removeShop/{id}", method=RequestMethod.POST)
	public User removeShop(@RequestBody Shops shop, @PathVariable("id") String userId) {
		User user;
		user = userService.getUserbyId(userId);

		return userService.removeShopFromUser(shop, user);
	}
}