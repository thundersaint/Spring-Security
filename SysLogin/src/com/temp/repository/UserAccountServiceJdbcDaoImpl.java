/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class UserAccountServiceJdbcDaoImpl extends JdbcDaoImpl {	
	@Override
	protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) 
	{
		User User = (User) userFromUserQuery ;
		return new User(User.getUsername(), User.getPassword(), User.isEnabled(), User.isAccountNonExpired(), User.isCredentialsNonExpired(), User.isAccountNonLocked(), combinedAuthorities) ;
	}
	
	@Override
	protected List<UserDetails> loadUsersByUsername(String username) throws DataAccessException
	{
		List<User> tempList = getJdbcTemplate().query("SELECT * FROM users WHERE username = ?",
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
		
		List<UserDetails> newList = new ArrayList<UserDetails>() ;
		newList.addAll(tempList) ;
		return newList ;
	}
}
