package com.cg.spblaguna.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceptionistReqDTO {
    private String receptionistName;
    private LocalDate dob;
    private String email;
    private String phone;
    private LocalDate createAt;
    private String avatarImg;
    private String receptionistInfo;

}
