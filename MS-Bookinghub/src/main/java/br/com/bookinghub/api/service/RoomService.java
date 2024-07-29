package br.com.bookinghub.api.service;

import br.com.bookinghub.api.dto.RoomDTO;
import br.com.bookinghub.api.model.Room;
import br.com.bookinghub.api.model.exception.ResourceNotFoundException;
import br.com.bookinghub.api.repository.RoomRepository;
import br.com.bookinghub.api.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Method to retrieve all rooms.
     * @return every room from the database.
     */
    @Transactional(readOnly = true)
    public List<RoomDTO> findAllRooms() {
        List<Room> list = repository.findAll();
        return list.stream().map(room -> new ModelMapper().map(room, RoomDTO.class)).collect(Collectors.toList());
    }

    /**
     * Method that return a room by its ID.
     * @param id of the searched room.
     * @return a room.
     */
    @Transactional(readOnly = true)
    public Optional<RoomDTO> findRoomById(Long id) {
        // Obtaining Room Optional by its ID.
        Optional<Room> room = repository.findById(id);
        // If it couldn't find, throw an Exception
        if (room.isEmpty()) {
            throw new ResourceNotFoundException("Room ID " + id + " could not be found.");
        }
        // Convert the found Optional into a DTO.
        RoomDTO dto = new ModelMapper().map(room.get(), RoomDTO.class);
        // Creating and returning an Optional of DTO.
        return Optional.of(dto);
    }


    /**
     * Method that adds a room to the database
     * @param roomDto to be added
     * @return the added room
     */
    public RoomDTO addRoom (RoomDTO roomDto) {
        Room room = new ModelMapper().map(roomDto, Room.class);
        repository.save(room);
        return new RoomDTO(room);
    }

    /**
     * Deletes a room from the databases
     * @param id from the room to be deleted
     */
    public void removeRoom (Long id) {
        if (findRoomById(id).isEmpty()) {
            throw new ResourceNotFoundException("Room ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    /**
     * Method that updates a room on the database
     * @param id of the room to be updated
     * @param roomDto to be updated
     * @return room after being updated on the database
     */
    public RoomDTO updateRoom (Long id, RoomDTO roomDto) {
        if (findRoomById(id).isEmpty()) {
            throw new ResourceNotFoundException("Room ID " + id + " not found");
        }
        /**
         * Instantiating a variable of type Room with name room
         * which receives a ModelMapper and calls map method to
         * copy RoomDTO information received on the request.
         */
        roomDto.setId(id);
        Room room = new ModelMapper().map(roomDto, Room.class);
        room = repository.save(room);
        return new RoomDTO(room);
    }
}
