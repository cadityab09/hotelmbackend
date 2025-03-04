package org.spacademy.hotelmanagement.services.admin.rooms;

import org.spacademy.hotelmanagement.dto.RoomDto;
import org.spacademy.hotelmanagement.dto.RoomResponseDto;

public interface RoomsService {
	boolean postRoom(RoomDto roomDto);
	RoomResponseDto getAllRooms(int pageNumber);
	RoomDto getRoomById(Long id);
	boolean updateRoom(long id, RoomDto roomDto);
	void deleteRoom(Long id);
}
