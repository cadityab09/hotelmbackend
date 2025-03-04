package org.spacademy.hotelmanagement.services.customer.booking;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

import org.spacademy.hotelmanagement.dto.ReservationDto;
import org.spacademy.hotelmanagement.dto.ReservationResponseDto;
import org.spacademy.hotelmanagement.entity.Reservation;
import org.spacademy.hotelmanagement.entity.Room;
import org.spacademy.hotelmanagement.entity.User;
import org.spacademy.hotelmanagement.enums.ReservationStatus;
import org.spacademy.hotelmanagement.repository.ReservationRepository;
import org.spacademy.hotelmanagement.repository.RoomRepository;
import org.spacademy.hotelmanagement.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	
	private final UserRepository userRepository;
	
	private final RoomRepository roomRepository;
	
	private final ReservationRepository reservationRepository;
	
	public static final int SEARCH_RESULT_PER_PAGE = 4;

	
	@Override
	public boolean postReservation(ReservationDto reservationDto) {
		
		Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
		
		Optional<Room> optionalRoom = roomRepository.findById(reservationDto.getRoomId());
		
		if(optionalUser.isPresent() && optionalRoom.isPresent()) {
			
			Reservation reservation = new Reservation();
			
			reservation.setUser(optionalUser.get());
			reservation.setRoom(optionalRoom.get());
			reservation.setCheckInDate(reservationDto.getCheckInDate());
			reservation.setCheckOutDate(reservationDto.getCheckOutDate());
			
			Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());
			
			reservation.setPrice(optionalRoom.get().getPrice() * days);
			
			reservation.setReservationStatus(ReservationStatus.PENDING);
			
			reservationRepository.save(reservation);
			
			return true;
		}
		
		return false;
	}
	
	public ReservationResponseDto getAllReservationsByUserId(Long userId, int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
		
		Page<Reservation> reservationPage = reservationRepository.findAllByUserId(pageable, userId);
		
		ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
		
		reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));
		
		reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
		
		reservationResponseDto.setTotalPages(reservationPage.getTotalPages());
		
		return reservationResponseDto;

	}

}
