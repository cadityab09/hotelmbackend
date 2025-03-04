package org.spacademy.hotelmanagement.services.customer.room;

import org.spacademy.hotelmanagement.dto.RoomResponseDto;

public interface RoomService {
	 RoomResponseDto getAvailableRooms(int pageNumber);
}
