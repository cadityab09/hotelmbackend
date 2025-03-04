package org.spacademy.hotelmanagement.services.admin.rooms;

import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;

import org.spacademy.hotelmanagement.dto.RoomDto;
import org.spacademy.hotelmanagement.dto.RoomResponseDto;
import org.spacademy.hotelmanagement.entity.Room;
import org.spacademy.hotelmanagement.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService {

	private final RoomRepository roomRepository;
	
	public boolean postRoom(RoomDto roomDto) {
		try {
			Room room = new Room();
			room.setName(roomDto.getName());
			room.setPrice(roomDto.getPrice());
			room.setType(roomDto.getType());
			room.setAvailable(true);
			
			roomRepository.save(room);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public RoomResponseDto getAllRooms(int pageNumber) {
		
		Pageable pageable=PageRequest.of(pageNumber, 6);
		Page<Room> roomPage = roomRepository.findAll(pageable);
		
		RoomResponseDto roomResponseDto=new RoomResponseDto();
		
		roomResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
		roomResponseDto.setTotalPages(roomPage.getTotalPages());
		roomResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));
		
		return roomResponseDto;
		
	}
	
	public RoomDto getRoomById(Long id) {
		Optional<Room> optionalRoom = roomRepository.findById(id);
		if(optionalRoom.isPresent()) {
			return optionalRoom.get().getRoomDto();
		} else {
			throw new EntityNotFoundException("Room not present");
		}
	}
	
	public boolean updateRoom(long id, RoomDto roomDto) {
		
		Optional<Room> optionalRoom = roomRepository.findById(id);
		
		if(optionalRoom.isPresent()) {
		
			Room room = optionalRoom.get();
			
			room.setName(roomDto.getName());
			room.setType(roomDto.getType());
			room.setPrice(roomDto.getPrice());
			
			roomRepository.save(room);
			
			return true;
		}
		return false;
	}
	
	
	public void deleteRoom(Long id) {
		
		Optional<Room> optionalRoom = roomRepository.findById(id);
		
		if(optionalRoom.isPresent()) {
			roomRepository.deleteById(id);
		} else {
			throw new EntityNotFoundException("Room not found");
		}
	}
}
