/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.component;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.temp.repository.UserAccountRepositoryImpl;
import com.temp.repository.UserAuthorityRepositoryImpl;

public class AuthenticationUserDetailGetter implements UserDetailsService {
    private UserAccountRepositoryImpl userRepository ;
    
    private UserAuthorityRepositoryImpl userAuthorityRepository ;

    protected AuthenticationUserDetailGetter() {
    }

    public AuthenticationUserDetailGetter(UserAccountRepositoryImpl userRepository, UserAuthorityRepositoryImpl userAuthorityRepository) {
        this.userRepository = userRepository ;
        this.userAuthorityRepository = userAuthorityRepository ;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        User user = userRepository.findByLogin(username) ;
        List<GrantedAuthority> combinedAuthorities = userAuthorityRepository.getUserAuthorities(username) ;
        
        validateUser(user, username) ;
        
        user = new User(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), combinedAuthorities)  ;
        
        return new AuthenticationUserDetails(user) ;
    }

    private void validateUser(User user, String login) {
        if (user == null) {
            throw new UsernameNotFoundException("User with login " + login + "  has not been found.") ;
        }
    }
}

