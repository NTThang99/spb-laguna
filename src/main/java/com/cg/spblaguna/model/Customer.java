package com.cg.spblaguna.model;

import com.cg.spblaguna.model.enumeration.EStatusUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    private LocalDate dob;

    @NotEmpty(message = "email không được trống")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "Tên không được trống")
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty(message = "Tên không được trống")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Số điện thoại không được trống")
    private String phone;

    @NotEmpty(message = "Địa chỉ không được để trống")
    private String address;

    @Enumerated(EnumType.STRING)
    private EStatusUser statusUser;
    @OneToOne
    @JoinColumn(name = "cardpayment_id", nullable = false)
    private CardPayment cardpayment;
    private int deleted = 0;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

}
