package com.cg.spblaguna.model.dto.req;

import com.cg.spblaguna.model.enumeration.EStatusUser;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class CustomerReqDTO {
    private Date dob;
    private String email;
    private String firstName;
    private String lastName;
    private Long phone;
    private String address;
    private EStatusUser statusUser;
    private String password;
}
