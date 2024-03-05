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
    private Date dob;
    private String email;
    private Long phone;
    private LocalDate createAt;
    private String avatarImg;
    private String receptionistInfo;
}
