package org.spacademy.hotelmanagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.spacademy.hotelmanagement.dto.ReservationDto;
import org.spacademy.hotelmanagement.enums.ReservationStatus;

import lombok.Data;

@Entity
@Data
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private Long price;
	
	private ReservationStatus reservationStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "room_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Room room;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	
	public ReservationDto getReservationDto() {
		
		ReservationDto reservationDto = new ReservationDto();
		
		reservationDto.setId(id);
		reservationDto.setCheckInDate(checkInDate);
		reservationDto.setCheckOutDate(checkOutDate);
		reservationDto.setPrice(price);
		reservationDto.setReservationStatus(reservationStatus);
		
		reservationDto.setUserId(user.getId());
		reservationDto.setUserName(user.getName());
		
		reservationDto.setRoomId(room.getId());
		reservationDto.setRoomName(room.getName());
		reservationDto.setRoomType(room.getType());
		
		return reservationDto;
	}
}
