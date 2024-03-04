package com.cg.spblaguna.model;

import com.cg.spblaguna.model.dto.res.RoomResDTO;
import jakarta.persistence.*;
import lombok.*;

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
    private Long pricePerNight;

    private Long acreage;

    private Long sleep;


    private String description;



    @Column(name = "utilitie", columnDefinition = "json", nullable = false)
    private String utilitie;


    private Float rate;

    public Room(String name, ERoomType roomType, EStatusRoom statusRoom, EViewType viewType, Long pricePerNight, Long acreage, Long sleep, String description, String utilitie, KindOfRoom kindOfRoom, Float rate, PerType perType) {
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

    public Room(String name, ERoomType roomType, EStatusRoom statusRoom, EViewType viewType, Long pricePerNight, Long acreage, Long sleep, String description, String utilitie, KindOfRoom kindOfRoom, PerType perType) {
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
