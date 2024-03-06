package com.cg.spblaguna.service.booking;

import com.cg.spblaguna.model.Booking;
import com.cg.spblaguna.model.enumeration.EBookStatus;
import com.cg.spblaguna.service.IGeneralService;

import java.util.List;

public interface IBookingService extends IGeneralService<Booking,Long> {
    List<Booking> findAllByCustomerId(Long customerId);
    List<Booking>findByRoomIdAndBookingDate(Long roomId, String bookingDate);
}
