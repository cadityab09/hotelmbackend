package org.spacademy.hotelmanagement.controller.customer;

import org.spacademy.hotelmanagement.dto.ReservationDto;
import org.spacademy.hotelmanagement.services.customer.booking.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class BookingController {

	private final BookingService bookingService;
	
	@PostMapping("/book")
	public ResponseEntity<?> postBooking(@RequestBody ReservationDto reservationDto){
		boolean success =  bookingService.postReservation(reservationDto);
		
		if(success) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/bookings/{userId}/{pageNumber}")
	public ResponseEntity<?> getAllReservationsByUserId(@PathVariable Long userId, @PathVariable int pageNumber) {
		try {
			return ResponseEntity.ok(bookingService.getAllReservationsByUserId(userId, pageNumber));
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
		}
	}
}
