package org.spacademy.hotelmanagement.dto;

import org.spacademy.hotelmanagement.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {
	private String jwt;
	private Long userId;
	private UserRole userRole;
}
