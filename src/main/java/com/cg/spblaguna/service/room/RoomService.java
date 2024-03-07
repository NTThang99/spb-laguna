package com.cg.spblaguna.service.room;

import com.cg.spblaguna.model.Image;
import com.cg.spblaguna.model.KindOfRoom;
import com.cg.spblaguna.model.PerType;
import com.cg.spblaguna.model.Room;
import com.cg.spblaguna.model.dto.req.RoomReqDTO;
import com.cg.spblaguna.model.dto.res.RoomResDTO;
import com.cg.spblaguna.model.enumeration.EImageType;
import com.cg.spblaguna.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IKindOfRoomRepository kindOfRoomRepository;

    @Autowired
    private IPerTypeRepository perTypeRepository;

    @Autowired
    private IRateRepository rateRepository;

    @Autowired
    private IImageRepository imageRepository;

    public List<RoomResDTO> getRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(Room::toRoomResDto).collect(Collectors.toList());
    }

    public RoomResDTO save(RoomReqDTO roomReqDTO) {
        KindOfRoom kindOfRoom = kindOfRoomRepository.findById(roomReqDTO.getKingOfRoomId()).get();
        PerType perType = perTypeRepository.findById(roomReqDTO.getPerTypId()).get();

        Room r = new Room(roomReqDTO.getName(),roomReqDTO.getRoomType()
                ,roomReqDTO.getStatusRoom(),
                roomReqDTO.getViewType(),roomReqDTO.getPricePerNight()
                ,roomReqDTO.getAcreage(),
                roomReqDTO.getSleep(),roomReqDTO.getDescription(),
                roomReqDTO.getUtilitie(),kindOfRoom,perType);
        Room room = roomRepository
                .save(r);

        roomReqDTO.getImageIds().forEach(s -> {
            Image image = imageRepository.findById(s).get();
            image.setImageType(EImageType.ROOM);
            image.setRoomImage(r);
            imageRepository.save(image);
        });
        return room.toRoomResDto();
    }

    public RoomResDTO update(RoomReqDTO roomReqDTO){
        KindOfRoom kindOfRoom = kindOfRoomRepository.findById(roomReqDTO.getKingOfRoomId()).get();
        PerType perType = perTypeRepository.findById(roomReqDTO.getPerTypId()).get();
        Room room = roomRepository.findById(roomReqDTO.getId()).orElseThrow();
        room.setName(roomReqDTO.getName());
        room.setRoomType(roomReqDTO.getRoomType());
        room.setStatusRoom(roomReqDTO.getStatusRoom());
        room.setViewType(roomReqDTO.getViewType());
        room.setPricePerNight(roomReqDTO.getPricePerNight());
        room.setAcreage(roomReqDTO.getAcreage());
        room.setDescription(roomReqDTO.getDescription());
        room.setUtilitie(roomReqDTO.getUtilitie());
        room.setKingOfRoom(kindOfRoom);
        room.setPerType(perType);



        roomRepository.save(room);
        return room.toRoomResDto();
    }

    public void delete(Long id) {
        Room room = roomRepository.findById(id).get();
        roomRepository.delete(room);
    }


    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public void change(Room room) {
        roomRepository.save(room);
    }
}
