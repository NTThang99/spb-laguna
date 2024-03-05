package com.cg.spblaguna.model;

import com.cg.spblaguna.model.enumeration.EBookStatus;
import com.cg.spblaguna.model.enumeration.EDepositedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "create_at")
    private Timestamp createAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "check_in")
    private Timestamp checkIn;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "check_out")
    private Timestamp checkOut;

    private Long total;

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
}
