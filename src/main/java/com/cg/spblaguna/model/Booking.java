package com.cg.spblaguna.model;

import com.cg.spblaguna.model.enumeration.EBookStatus;
import com.cg.spblaguna.model.enumeration.EDepositedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private String country;

    @Column(name = "zip_code")
    private String zipCode;

    private Long phone;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "create_at")
    private Timestamp createAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "check_in")
    private Timestamp checkIn;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "check_out")
    private Timestamp checkOut;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_status")
    private EBookStatus bookStatus;

    @Column(name = "deposited_number")
    private Long depositedNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "deposited_status")
   private EDepositedStatus depositedStatus;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
   private Room room;

    private Boolean reminderSent = false ;
    @OneToOne
    private Payment payment;
    private String email;
}
