package com.cg.spblaguna.service.booking;

import com.cg.spblaguna.model.Booking;
import com.cg.spblaguna.repository.IBookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService implements IBookingService {
    @Autowired
    private IBookingRepository bookingRepository;

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Booking> findAllByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public List<Booking> findByRoomIdAndBookingDate(Long roomId, String bookingDate) {
        return null;
    }


}
