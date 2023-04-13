package be.bstorm.akimts.reservaroom.service.impl;

import be.bstorm.akimts.reservaroom.exceptions.ResourceNotFound;
import be.bstorm.akimts.reservaroom.models.BookingStatus;
import be.bstorm.akimts.reservaroom.models.entity.Booking;
import be.bstorm.akimts.reservaroom.models.entity.Room;
import be.bstorm.akimts.reservaroom.models.entity.User;
import be.bstorm.akimts.reservaroom.models.form.BookingCreateForm;
import be.bstorm.akimts.reservaroom.repository.BookingRepository;
import be.bstorm.akimts.reservaroom.repository.RoomRepository;
import be.bstorm.akimts.reservaroom.repository.UserRepository;
import be.bstorm.akimts.reservaroom.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(UserRepository userRepository, RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void create(BookingCreateForm form) {
        Booking booking = form.toEntity();

        if( !form.getBeginsAt().isBefore(form.getEndsAt()) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "start should be before end");

        if( bookingRepository.existsWithConflict(form.getRoomId(), form.getDate(), form.getBeginsAt(), form.getEndsAt()) )
            throw new ResponseStatusException(HttpStatus.CONFLICT, "room is already used at that time"); //

        booking.setBookedBy(
                userRepository.findById(form.getUserId()).
                        orElseThrow(() -> new ResourceNotFound(User.class))
        ) ;

        booking.setRoom(
                roomRepository.findById(form.getRoomId())
                        .orElseThrow(() -> new ResourceNotFound(Room.class))
        );

        booking.setStatus(BookingStatus.PENDING);

        bookingRepository.save( booking );
    }

    @Override
    public void updateStatus(long id, BookingStatus status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(Booking.class));

        if( status == BookingStatus.PENDING )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cannot change a status to PENDING");

        if( booking.getStatus() != BookingStatus.PENDING )
            throw new ResponseStatusException(HttpStatus.CONFLICT, "cant change the status of a booking that is not PENDING");

        booking.setStatus(status);
        bookingRepository.save(booking);
    }
}
