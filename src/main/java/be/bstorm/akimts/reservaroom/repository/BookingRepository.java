package be.bstorm.akimts.reservaroom.repository;

import be.bstorm.akimts.reservaroom.models.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
        SELECT COUNT(b) > 0
        FROM Booking b
        WHERE
            b.date = :date AND
            ( b.beginsAt <= :end AND b.endsAt >= :start ) AND
            b.room.id = :roomId
    """)
    boolean existsWithConflict(Long roomId, LocalDate date, LocalTime start, LocalTime end);

}
