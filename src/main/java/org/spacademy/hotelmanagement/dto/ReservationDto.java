package org.spacademy.hotelmanagement.dto;

import java.time.LocalDate;

import org.spacademy.hotelmanagement.enums.ReservationStatus;

import lombok.Data;

@Data
public class ReservationDto {

	private long id;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private Long price;

	private ReservationStatus reservationStatus;
	
	private Long roomId;
	
	private String roomName;
	
	private String roomType;
	
	private Long userId;
	
	private String userName;
	
}
