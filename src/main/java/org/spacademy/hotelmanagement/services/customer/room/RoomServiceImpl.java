package org.spacademy.hotelmanagement.services.customer.room;

import java.util.stream.Collectors;

import org.spacademy.hotelmanagement.dto.RoomResponseDto;
import org.spacademy.hotelmanagement.entity.Room;
import org.spacademy.hotelmanagement.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomRepository roomRepository;
	
	public RoomResponseDto getAvailableRooms(int pageNumber) {
		
		Pageable pageable=PageRequest.of(pageNumber, 6);
		Page<Room> roomPage = roomRepository.findByAvailable(true, pageable);
		
		RoomResponseDto roomResponseDto=new RoomResponseDto();
		
		roomResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
		roomResponseDto.setTotalPages(roomPage.getTotalPages());
		roomResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));
		
		return roomResponseDto;
		
	}
	
	
}
