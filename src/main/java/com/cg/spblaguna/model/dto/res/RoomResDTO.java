package com.cg.spblaguna.model.dto.res;

import com.cg.spblaguna.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class RoomResDTO {
    private Long id;

    private String name;

    private ERoomType roomType;

    private EStatusRoom statusRoom;

    private EViewType viewType;

    private KindOfRoom kingOfRoom;

    private PerType perType;

    private Long pricePerNight;

    private Long acreage;

    private Long sleep;

    private String description;

    private String utilitie;

    private Rate rate;
}
