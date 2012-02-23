/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.component;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class AuthenticationUserDetails implements UserDetails {
    private final String login ;
    private final String passwordHash ;
    private final boolean enabled ; 
    private HashSet<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>() ;

    public AuthenticationUserDetails(User user) {
        this.login = user.getUsername() ;
        this.passwordHash = user.getPassword() ;
        this.enabled = user.isEnabled() ;
        this.grantedAuthorities.addAll(user.getAuthorities()) ;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities ;
    }

    @Override
    public String getPassword() {
        return passwordHash ;
    }

    @Override
    public String getUsername() {
        return login ;
    }

    @Override
    public boolean isEnabled() {
        return enabled ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }

    public String getLogin() {
        return login ;
    }
}

