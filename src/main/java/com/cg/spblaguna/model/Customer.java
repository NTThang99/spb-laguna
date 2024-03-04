package com.cg.spblaguna.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dob;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Long phone;

    private String address;

    @Enumerated(EnumType.STRING)
    private EStatusUser statusUser;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "cardpayment_id", nullable = false)
    private CardPayment cardpayment;
    private int deleted = 0;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;



}
