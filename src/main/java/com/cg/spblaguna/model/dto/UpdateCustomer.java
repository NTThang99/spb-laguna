package com.cg.spblaguna.model.dto;

import com.cg.spblaguna.model.EStatusUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomer {
    private Date dob;
    private String email;
    private String firstName;
    private String lastName;
    private Long phone;
    private String address;
    private EStatusUser statusUser;
}
