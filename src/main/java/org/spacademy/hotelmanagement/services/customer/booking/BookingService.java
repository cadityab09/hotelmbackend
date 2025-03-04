package org.spacademy.hotelmanagement.services.customer.booking;

import org.spacademy.hotelmanagement.dto.ReservationDto;
import org.spacademy.hotelmanagement.dto.ReservationResponseDto;

public interface BookingService {

	boolean postReservation(ReservationDto reservationDto);
	
	ReservationResponseDto getAllReservationsByUserId(Long userId, int pageNumber);
}
