package org.webproject.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.webproject.model.User;

public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	public UserDaoImpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}

	@Override
	public int registerUser(User user)
	{
		
		String sql = "INSERT INTO USER_DATA VALUES(?,?,AES_ENCRYPT(?,'123'))";

		try {
			
			int counter = jdbcTemplate.update(sql, new Object[] { user.getUserId(), user.getemail(),user.getPassword() });

			return counter;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String loginUser(User user) {
		
		String sql = "SELECT USER_ID FROM USER_DATA WHERE USER_EMAIL=? AND CAST(AES_DECRYPT(USER_PASS,'123')as CHAR)=?";
		try {

			String userId = jdbcTemplate.queryForObject(sql, new Object[] {
					user.getemail(), user.getPassword() }, String.class);

			return userId;
			
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
			return null;
		}
	}
}
