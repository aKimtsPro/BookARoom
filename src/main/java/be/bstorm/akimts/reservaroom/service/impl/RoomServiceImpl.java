package be.bstorm.akimts.reservaroom.service.impl;

import be.bstorm.akimts.reservaroom.models.dto.RoomDTO;
import be.bstorm.akimts.reservaroom.repository.RoomRepository;
import be.bstorm.akimts.reservaroom.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> getAll() {
        return roomRepository.findAll().stream()
                .map( RoomDTO::from )
                .toList();
    }
}
