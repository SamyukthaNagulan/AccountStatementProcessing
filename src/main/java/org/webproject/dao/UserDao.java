package org.webproject.dao;

import org.webproject.model.User;

public interface UserDao {

	public int registerUser(User user);

	public String loginUser(User user);

}
