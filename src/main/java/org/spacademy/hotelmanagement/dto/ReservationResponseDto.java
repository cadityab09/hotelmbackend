package org.spacademy.hotelmanagement.dto;

import java.util.List;

import lombok.Data;

@Data
public class ReservationResponseDto {

	private Integer totalPages;
	
	private Integer pageNumber;
	
	private List<ReservationDto> reservationDtoList;
}
