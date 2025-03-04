package org.spacademy.hotelmanagement.dto;

import org.spacademy.hotelmanagement.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

	private long id;
	private String email;
	private String name;
	private UserRole userRole;
}
