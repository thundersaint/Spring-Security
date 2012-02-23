/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.component;

import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

public class PasswordSecurerComponent {

	private ShaPasswordEncoder passwordEncoder ;

	private ReflectionSaltSource saltSource ;
		
	public PasswordSecurerComponent(ShaPasswordEncoder passwordEncoder, ReflectionSaltSource saltSource) {
		super();
		this.passwordEncoder = passwordEncoder ;
		this.saltSource = saltSource ;
	}

	public String makeEncodePassword(UserDetails user)
	{
		String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(user));
		return encodedPassword ;
	}
}