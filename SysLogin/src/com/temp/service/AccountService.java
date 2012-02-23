/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.temp.repository.UserAccountRepositoryImpl;
import com.temp.repository.UserAuthorityRepositoryImpl;

@Service
public class AccountService {
	@Autowired
	private UserAccountRepositoryImpl userRepository;
	
	@Autowired
	private UserAuthorityRepositoryImpl userAuthorityRepositoryImpl ;
			
    public User findByLogin(String username) {
    	return userRepository.findByLogin(username);
    }
    
    public void createUserAccount(String username, String password)
    {	
    	User user = new User(
    			username, 
    			password, 
				true, 
				true,
				true,
				true,
				AuthorityUtils.NO_AUTHORITIES
			) ;
    	
    	userRepository.addRow(user) ;
    	
    	GrantedAuthorityImpl auth = new GrantedAuthorityImpl("ROLE_USER") ;
    	userAuthorityRepositoryImpl.addRow(user, auth) ;
    }
    
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isAuthenticated(authentication) ;
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() ;
    }
}
