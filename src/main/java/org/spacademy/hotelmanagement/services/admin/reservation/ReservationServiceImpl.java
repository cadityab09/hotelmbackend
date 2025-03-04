package org.spacademy.hotelmanagement.services.admin.reservation;

import java.util.Optional;
import java.util.stream.Collectors;

import org.spacademy.hotelmanagement.dto.ReservationResponseDto;
import org.spacademy.hotelmanagement.entity.Reservation;
import org.spacademy.hotelmanagement.entity.Room;
import org.spacademy.hotelmanagement.enums.ReservationStatus;
import org.spacademy.hotelmanagement.repository.ReservationRepository;
import org.spacademy.hotelmanagement.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	private final ReservationRepository reservationRepository;
	
	private final RoomRepository roomRepository;
	
	public static final int SEARCH_RESULT_PER_PAGE = 4;
	
	
	public ReservationResponseDto getAllReservations(int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
		
		Page<Reservation> reservationPage = reservationRepository.findAll(pageable);
		
		ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
		
		reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));
		
		reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
		
		reservationResponseDto.setTotalPages(reservationPage.getTotalPages());
		
		return reservationResponseDto;
		
	}
	
	public boolean changeReservationStatus(Long id, String status) {
		
		Optional<Reservation> optionalReservation = reservationRepository.findById(id);
		
		
		if(optionalReservation.isPresent()) {
			
			Reservation existingReservation = optionalReservation.get();
			
			if(status.equals("Approve")) {
				existingReservation.setReservationStatus(ReservationStatus.APPROVED);
			} else {
				existingReservation.setReservationStatus(ReservationStatus.REJECTED);
			}
			
			reservationRepository.save(existingReservation);
			
			
			Room existingRoom = existingReservation.getRoom();
			existingRoom.setAvailable(false);
			
			roomRepository.save(existingRoom);
			
			return true;
			
		}
		
		return false;
	}
}
