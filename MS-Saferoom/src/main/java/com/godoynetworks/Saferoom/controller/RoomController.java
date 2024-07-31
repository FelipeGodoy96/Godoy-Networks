package com.godoynetworks.Saferoom.controller;


import com.godoynetworks.Saferoom.dto.RoomDTO;
import com.godoynetworks.Saferoom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> rooms = service.findAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RoomDTO>> getRoomById (Long id) {
        Optional<RoomDTO> roomDto = service.findRoomById(id);
        return new ResponseEntity<>(roomDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom (@RequestBody RoomDTO roomDto) {
        roomDto = service.addRoom(roomDto);
        return new ResponseEntity<>(roomDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroyRoom (@PathVariable Long id) {
        service.removeRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> refreshRoom (@PathVariable Long id, @RequestBody RoomDTO roomDto) {
        roomDto = service.updateRoom(id, roomDto);
        return new ResponseEntity<>(roomDto, HttpStatus.OK);
    }


}
