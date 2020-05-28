package ru.cs.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.cs.entity.SUser;
import ru.cs.entity.dao.UserDao;
import ru.cs.entity.dao.UserRoleDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public User loadUserByUsername(String userName) throws UsernameNotFoundException {
		SUser sUser = this.userDao.findUserAccount(userName);

		if (sUser == null) {
			System.out.println("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}

		System.out.println("Found User: " + sUser.getName());

		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = this.userRoleDao.getRoleNames(sUser.getId());

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		User user = new User(sUser.getName(), sUser.getPassword(), grantList);
		user.setId(sUser.getId());

		return user;
	}

}
