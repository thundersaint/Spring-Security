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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;

public class UserAuthorityRepositoryImpl extends AbstractRepositoryImpl<GrantedAuthorityImpl> {
	
	public UserAuthorityRepositoryImpl(JdbcTemplate jdbcTemplate) throws Exception {		
		super(
			jdbcTemplate,
			"SELECT * FROM authorities WHERE username = ?", 
			"INSERT INTO authorities (username, authority) VALUES (?, ?)",
			new RowMapper<GrantedAuthorityImpl>() {
				public GrantedAuthorityImpl mapRow(ResultSet rs , int i)  throws SQLException {
					return new GrantedAuthorityImpl(rs.getString("authority")) ;
				}
			}
		) ;
	}

	@Override
	public GrantedAuthorityImpl addRow(GrantedAuthorityImpl t) throws DataAccessException {
		return null ;
	}

	public void addRow(User u, GrantedAuthorityImpl t) throws DataAccessException {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(getJdbcTemplate());
		simpleJdbcInsert.setTableName("authorities") ;
		simpleJdbcInsert.setColumnNames(Arrays.asList("username", "authority")) ;
		
		Map<String, Object> args = new HashMap<String, Object>() ;
		args.put("username", u.getUsername()) ;
		args.put("authority", t.getAuthority()) ;
		
		simpleJdbcInsert.execute(args);
	}
	
	public List<GrantedAuthority> getUserAuthorities(String username) throws DataAccessException {
		return getJdbcTemplate().query("SELECT * FROM authorities WHERE username = ?",
				new String[] { username }, new RowMapper<GrantedAuthority>() {
					public GrantedAuthority mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						GrantedAuthority authority = new GrantedAuthorityImpl(rs.getString("authority")) ;
						return authority ;
					}
				});
	}
}
