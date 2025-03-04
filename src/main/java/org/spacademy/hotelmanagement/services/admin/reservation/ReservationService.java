package org.spacademy.hotelmanagement.services.admin.reservation;

import org.spacademy.hotelmanagement.dto.ReservationResponseDto;

public interface ReservationService {

	ReservationResponseDto getAllReservations(int pageNumber);
	
	boolean changeReservationStatus(Long id, String status);
}
