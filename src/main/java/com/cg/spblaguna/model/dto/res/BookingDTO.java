package com.cg.spblaguna.model.dto.res;

import com.cg.spblaguna.model.Payment;
import com.cg.spblaguna.model.Room;
import com.cg.spblaguna.model.enumeration.EBookStatus;
import com.cg.spblaguna.model.enumeration.EDepositedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private String zipCode;
    private Long phone;
    private Timestamp createAt;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private BigDecimal total;
    private EBookStatus bookStatus;
    private BigDecimal depositedNumber;
    private EDepositedStatus depositedStatus;
    private Long roomId;
    private Long paymentId;
    private String email;
}
