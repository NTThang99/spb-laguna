package com.cg.spblaguna.model;

import com.cg.spblaguna.model.dto.res.RoomResDTO;
import com.cg.spblaguna.model.enumeration.ERoomType;
import com.cg.spblaguna.model.enumeration.EStatusRoom;
import com.cg.spblaguna.model.enumeration.EViewType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private ERoomType roomType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_room")
    private EStatusRoom statusRoom;

    @Enumerated(EnumType.STRING)
    @Column(name = "view_type")
    private EViewType viewType;

    @OneToOne
    @JoinColumn(name = "king_of_room_id", nullable = false)
    private KindOfRoom kingOfRoom;

    @OneToOne
    @JoinColumn(name = "per_type_id", nullable = false)
    private PerType perType;

    @Column(name = "price_perNight")
    private BigDecimal pricePerNight;

    private BigDecimal acreage;

    private Integer sleep;


    private String description;



    @Column(name = "utilitie", columnDefinition = "json", nullable = false)
    private String utilitie;


    private Float rate;

    public Room(String name, ERoomType roomType, EStatusRoom statusRoom, EViewType viewType, BigDecimal pricePerNight, BigDecimal acreage, Integer sleep, String description, String utilitie, KindOfRoom kindOfRoom, Float rate, PerType perType) {
        this.name = name;
        this.roomType = roomType;
        this.statusRoom = statusRoom;
        this.viewType = viewType;
        this.pricePerNight = pricePerNight;
        this.acreage = acreage;
        this.sleep = sleep;
        this.description = description;
        this.utilitie = utilitie;
        this.kingOfRoom = kindOfRoom;
        this.rate = rate;
        this.perType = perType;
    }

    public Room(String name, ERoomType roomType, EStatusRoom statusRoom, EViewType viewType, BigDecimal pricePerNight, BigDecimal acreage, Integer sleep, String description, String utilitie, KindOfRoom kindOfRoom, PerType perType) {
        this.name = name;
        this.roomType = roomType;
        this.statusRoom = statusRoom;
        this.viewType = viewType;
        this.pricePerNight = pricePerNight;
        this.acreage = acreage;
        this.sleep = sleep;
        this.description = description;
        this.utilitie = utilitie;
        this.kingOfRoom = kindOfRoom;
        this.perType = perType;
    }




    public RoomResDTO toRoomResDto() {
        RoomResDTO roomResDTO = new RoomResDTO();
        roomResDTO.setId(id);
        roomResDTO.setName(name);
        roomResDTO.setRoomType(roomType);
        roomResDTO.setStatusRoom(statusRoom);
        roomResDTO.setViewType(viewType);
        roomResDTO.setKingOfRoom(kingOfRoom);
        roomResDTO.setPerType(perType);
        roomResDTO.setPricePerNight(pricePerNight);
        roomResDTO.setAcreage(acreage);
        roomResDTO.setSleep(sleep);
        roomResDTO.setDescription(description);
        roomResDTO.setUtilitie(utilitie);
        return roomResDTO;
    }

}
