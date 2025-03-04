package org.spacademy.hotelmanagement.controller.customer;

import org.spacademy.hotelmanagement.services.customer.room.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="api/customer")
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomService;
	
	@GetMapping("room/{pageNumber}")
	public ResponseEntity<?> getAvailableRooms(@PathVariable int pageNumber) {
		
		if(roomService == null)
		return ResponseEntity.ok("Null ahe re");
		return ResponseEntity.ok(roomService.getAvailableRooms(pageNumber));
	}
}
