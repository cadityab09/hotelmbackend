package org.spacademy.hotelmanagement.dto;

import lombok.Data;

@Data
public class RoomDto {
	private long id;
	private String name;
	private String type;
	private long price;
	private boolean available;

}
