package org.spacademy.hotelmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.spacademy.hotelmanagement.dto.RoomDto;

import lombok.Data;

@Entity
@Data
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String type;
	private long price;
	private boolean available;
	
	public RoomDto getRoomDto() {
		RoomDto roomDto=new RoomDto();
		
		roomDto.setId(id);
		roomDto.setName(name);
		roomDto.setType(type);
		roomDto.setPrice(price);
		roomDto.setAvailable(available);
		
		return roomDto;
	}
	
}
