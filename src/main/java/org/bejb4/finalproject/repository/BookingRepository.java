package org.bejb4.finalproject.repository;

import org.bejb4.finalproject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByUser_Id(Long id);
}
