package com.HFTest.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HFTest.dao.RoleRepository;
import com.HFTest.dao.ShopsRepository;
import com.HFTest.dao.UserRepository;
import com.HFTest.entities.Role;
import com.HFTest.entities.Shops;
import com.HFTest.entities.User;
import com.HFTest.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ShopsRepository shopsRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public ShopsRepository getShopsRepository() {
		return shopsRepository;
	}

	@Autowired
	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User u = null;
		try {
			u = getUser(email);
		} catch (UsernameNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if(u == null) {
			System.out.println("No such user!!");
			throw new UsernameNotFoundException("No such user "+ email);
		}
		else if(u.getRoles().isEmpty()) {
			System.out.println("User has no authorities!!");
			throw new UsernameNotFoundException("User "+ email + " has no authorities!");
		}
		
		u.setLastAccessDate(Calendar.getInstance().getTime());
		
		try {
			updateUser(u.getId(), u);
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User addUser(User user) {
		Set<Role> user_roles = new HashSet<Role>();
		Role r_u = roleRepository.findByName("user");
		user_roles.add(r_u);
		user.setRoles(user_roles);
		user.setCreationDate(new Date());
		user.setFavShops(new HashSet<Shops>());
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	@Override
	public User getUserbyId(String userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public User getUser(String email) {
		User u = userRepository.findByEmail(email);
		return userRepository.findByEmail(email);
	}

	@Override
	public void updateUser(String id, User user) {
		user.setId(id);
		Set<Role> user_roles = new HashSet<Role>();
		Role r_u = roleRepository.findByName("user");
		user_roles.add(r_u);
		user.setRoles(user_roles);
		userRepository.save(user);
	}

	@Override
	public boolean deleteUser(String userId) {
		User u = getUserbyId(userId);
		try {
			if (u != null) {
				userRepository.delete(u);
				return true;
			}else
				return false; 
		} catch (Exception e) {
			System.out.println("User does not exist!");
			return false;
		}	
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public boolean assignRoleToUser(Role role, User user) {
		Role assign_role = roleRepository.findByName(role.getName());

		if(assign_role == null) {
			return false;
		}
		
		User assign_user = userRepository.findByEmail(user.getEmail());
		
		if(assign_user == null) {
			return false;
		}
		
		assign_user.getRoles().add(assign_role);
		userRepository.save(assign_user);
		return true;
	}

	@Override
	public User assignShopToUser(Shops shop, User user) {
		Shops pref_shop = shopsRepository.findById(shop.getId());
		
		if(pref_shop == null) {
			return null;
		}
		
		User assign_user = userRepository.findByEmail(user.getEmail());
		
		if(assign_user == null) {
			return null;
		}

		assign_user.getFavShops().add(pref_shop);
		return userRepository.save(assign_user);
	}

	@Override
	public User removeShopFromUser(Shops shop, User user) {

		Shops rem_shop = shopsRepository.findById(shop.getId());
		
		if(rem_shop == null) {
			return null;
		}
		User user_rem = userRepository.findByEmail(user.getEmail());
		
		if(user_rem == null) {
			return null;
		}
		
		Set<Shops> user_favShop = new HashSet<Shops>();
		user_favShop = user_rem.getFavShops();
		
		for(Shops s : user_favShop) {
			if (s.getId().equals(rem_shop.getId())) {
				user_favShop.remove(s);
				break;
			}
		}
		
		return userRepository.save(user_rem);
	}
}