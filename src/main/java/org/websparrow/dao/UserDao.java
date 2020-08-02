package org.websparrow.dao;

import org.websparrow.model.User;

public interface UserDao {

	public int registerUser(User user);

	public String loginUser(User user);

}
