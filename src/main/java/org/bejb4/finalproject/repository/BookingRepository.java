package org.bejb4.finalproject.repository;

import org.bejb4.finalproject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
