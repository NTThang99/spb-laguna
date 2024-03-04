package com.cg.spblaguna.model.dto.req;

import com.cg.spblaguna.model.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomReqDTO {
    private Long id;

    @NotBlank
    @Length(min = 5, max = 10)
    private String name;

    private ERoomType roomType;

    private EStatusRoom statusRoom;

    private EViewType viewType;

    private Long kingOfRoomId;

    private Long perTypId;

    private Long pricePerNight;

    private Long acreage;

    private Long sleep;

    private String description;

    private String utilitie;

}
