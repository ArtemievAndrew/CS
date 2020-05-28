package ru.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.cs.entity.dao.UserDao;
import ru.cs.security.User;

@Component
public class PasswordNewService {
	@Autowired
	private UserDao userDao;

	public boolean execute(User user, String pswcur, String pswnew1, String pswnew2) {
		// user.setPassword(pswnew1);
		// repository.save(user);

		System.out.println(
				"user=" + user.getUsername() + ", pswcur=" + pswcur + ", pswnew1=" + pswnew1 + ", pswnew2=" + pswnew2);
		return false;
	}
}
