package be.bstorm.akimts.reservaroom.controller;

import be.bstorm.akimts.reservaroom.models.dto.RoomDTO;
import be.bstorm.akimts.reservaroom.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public List<RoomDTO> getAll(){
        return roomService.getAll();
    }

}
