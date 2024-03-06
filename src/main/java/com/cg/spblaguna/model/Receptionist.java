package com.cg.spblaguna.model;

import com.cg.spblaguna.model.dto.res.ReceptionistResDTO;
import com.cg.spblaguna.model.enumeration.ELockStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "receptionists")
@Accessors(chain = true)
public class Receptionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dob;

    @Column(unique = true, nullable = false)
    private String email;
    @NotEmpty(message = "Tên lễ tân không được trống")
    @Column(name = "receptionist_name")
    private String receptionistName;
    @Column(name = "create_at")
    private LocalDate createAt;

    private String phone;

    private String address;


    @Column(name = "deleted", columnDefinition = "int default 0")
    private int deleted = 0;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ELockStatus lockStatus = ELockStatus.UNLOCK;

    @Column(name = "receptionist_info", columnDefinition = "LONGTEXT")
    private String receptionistInfo;




    public ReceptionistResDTO toReceptionistResDTO(){
        return new ReceptionistResDTO()
                .setReceptionistName(receptionistName)
                .setDob(dob)
                .setEmail(email)
                .setPhone(phone)
                .setCreateAt(createAt)
                .setReceptionistInfo(receptionistInfo);

    }
}
