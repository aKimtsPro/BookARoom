package be.bstorm.akimts.reservaroom.controller;

import be.bstorm.akimts.reservaroom.models.BookingStatus;
import be.bstorm.akimts.reservaroom.models.form.BookingCreateForm;
import be.bstorm.akimts.reservaroom.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid BookingCreateForm form){
        bookingService.create(form);
    }


    @PatchMapping(value = "/{id:[0-9]+}/accept")
    public void acceptBooking(@PathVariable long id){
        bookingService.updateStatus(id, BookingStatus.ACCEPTED);
    }

    @PatchMapping(value = "/{id:[0-9]+}/refuse")
    public void refuseBooking(@PathVariable long id){
        bookingService.updateStatus(id, BookingStatus.REFUSED);
    }
}
