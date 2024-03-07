package com.cg.spblaguna.repository;

import com.cg.spblaguna.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingRepository extends JpaRepository<Booking,Long> {


}
