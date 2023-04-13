package be.bstorm.akimts.reservaroom.service;

import be.bstorm.akimts.reservaroom.models.BookingStatus;
import be.bstorm.akimts.reservaroom.models.form.BookingCreateForm;

public interface BookingService {

    void create(BookingCreateForm form);

    void updateStatus(long id, BookingStatus status);
}
