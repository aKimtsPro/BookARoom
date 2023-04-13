package be.bstorm.akimts.reservaroom.models.entity;

import be.bstorm.akimts.reservaroom.models.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false)
    private Long id;

    @Column(name = "booking_date", nullable = false)
    private LocalDate date;
    @Column(name = "booking_start", nullable = false)
    private LocalTime beginsAt;
    @Column(name = "booking_end", nullable = false)
    private LocalTime endsAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "booking_user_id", nullable = false)
    private User bookedBy;

    @ManyToOne
    @JoinColumn(name = "booking_room_id", nullable = false)
    private Room room;

}
