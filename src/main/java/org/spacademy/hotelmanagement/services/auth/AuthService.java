package org.spacademy.hotelmanagement.services.auth;

import org.spacademy.hotelmanagement.dto.SignupRequest;
import org.spacademy.hotelmanagement.dto.UserDto;

public interface AuthService {
	UserDto createUser(SignupRequest signupRequest);
}
