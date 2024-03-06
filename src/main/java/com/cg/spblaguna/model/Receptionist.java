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
    private Date dob;
    @Column(unique = true, nullable = false)
    private String email;
    @NotEmpty(message = "Tên lễ tân không được trống")
    @Column(name = "receptionist_name")
    private String receptionistName;
    @Column(name = "create_at")
    private LocalDate createAt;
    private Long phone;

    private String address;
    @Column(name = "avatar_img")
    private String avatarImg;

    @Column(name = "deleted", columnDefinition = "int default 0")
    private int deleted = 0;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private ELockStatus lockStatus;


    @Column(name = "receptionist_info", columnDefinition = "LONGTEXT")
    private String receptionistInfo;

    public ReceptionistResDTO toReceptionistResDTO(){
        return new ReceptionistResDTO()
                .setReceptionistName(receptionistName)
                .setDob(dob)
                .setEmail(email)
                .setPhone(phone)
                .setCreateAt(createAt)
                .setAvatarImg(avatarImg)
                .setReceptionistInfo(receptionistInfo);

    }
}
