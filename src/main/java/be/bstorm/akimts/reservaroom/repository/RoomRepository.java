package be.bstorm.akimts.reservaroom.repository;

import be.bstorm.akimts.reservaroom.models.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
