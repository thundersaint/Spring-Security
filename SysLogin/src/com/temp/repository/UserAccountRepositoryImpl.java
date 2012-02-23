/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.temp.component.PasswordSecurerComponent;

public class UserAccountRepositoryImpl extends AbstractRepositoryImpl<User> {	
	private PasswordSecurerComponent passwordSecurerComponent ;
	
	public UserAccountRepositoryImpl(JdbcTemplate jdbcTemplate, PasswordSecurerComponent passwordSecurerComponent) throws Exception {		
		super(
			jdbcTemplate,
			"SELECT * FROM users WHERE username = ?", 
			"INSERT INTO users (username, password, enabled) VALUES ( ? , ? , ?)",
			new RowMapper<User>() {
				public User mapRow(ResultSet rs , int i)  throws SQLException {
					return new User(
							rs.getString("username"), 
							rs.getString("password"), 
							rs.getBoolean("enabled"), 
							true,
							true,
							true,
							AuthorityUtils.NO_AUTHORITIES
						) ;
				}
			}
		) ;
		this.passwordSecurerComponent = passwordSecurerComponent ;
	}
	
	@Override
	public User addRow(User user)  throws DataAccessException {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(getJdbcTemplate());
		simpleJdbcInsert.setTableName("users") ;
		simpleJdbcInsert.setColumnNames(Arrays.asList("username", "password", "enabled")) ;
		simpleJdbcInsert.setGeneratedKeyName("id") ;
		
		String secPwd = passwordSecurerComponent.makeEncodePassword(user) ;
		
		Map<String, Object> args = new HashMap<String, Object>() ;
		args.put("username", user.getUsername()) ;
		args.put("password", secPwd) ;
		args.put("enabled", true) ;
		
		simpleJdbcInsert.execute(args);
		return findByLogin(user.getUsername()) ;
	}
	
	public List<User> getRowByUsername(String username) throws DataAccessException {
		return getJdbcTemplate().query("SELECT * FROM users WHERE username = ?",
				new String[] { username }, new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return new User(
								rs.getString("username"), 
								rs.getString("password"), 
								rs.getBoolean("enabled"), 
								true,
								true,
								true,
								AuthorityUtils.NO_AUTHORITIES
							) ;
					}
				});
	}
	
	public User findByLogin(String username) {
		List<User> users = this.getRowByUsername(username) ;
		if (users.size() > 0)
			return users.get(0) ;
		return null ;
	}
}
