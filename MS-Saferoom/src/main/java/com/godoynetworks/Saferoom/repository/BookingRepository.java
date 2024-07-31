package com.godoynetworks.Saferoom.repository;

import com.godoynetworks.Saferoom.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
