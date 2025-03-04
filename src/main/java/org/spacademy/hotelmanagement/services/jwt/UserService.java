package org.spacademy.hotelmanagement.services.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
	UserDetailsService userDetailsService();
}
