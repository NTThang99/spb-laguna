package com.cg.spblaguna.controller.api;

import com.cg.spblaguna.model.Booking;
import com.cg.spblaguna.model.enumeration.EBookStatus;
import com.cg.spblaguna.service.booking.BookingService;
import com.cg.spblaguna.service.booking.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingAPI {
    @Autowired
    private IBookingService bookingService;
    @GetMapping
    public ResponseEntity<?> getAllBooking() {
        List<Booking> bookingList = bookingService.findAll();
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }


}
