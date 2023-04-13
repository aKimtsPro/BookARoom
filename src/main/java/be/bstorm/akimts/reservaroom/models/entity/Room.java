package be.bstorm.akimts.reservaroom.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Long id;

    @Column(name = "room_number", nullable = false)
    private Long number;

    @Column(name = "room_capacity", nullable = false)
    private int capacity;

    @ManyToMany
    @JoinTable(
            name = "room_equipment",
            joinColumns = @JoinColumn(name = "room_equipment_room_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "room_equipment_equipment_id", nullable = false)
    )
    private List<Equipment> equipments = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings = new ArrayList<>();

}
