package com.cg.spblaguna.service;

import com.cg.spblaguna.model.KindOfRoom;
import com.cg.spblaguna.model.PerType;
import com.cg.spblaguna.model.Room;
import com.cg.spblaguna.model.dto.req.RoomReqDTO;
import com.cg.spblaguna.model.dto.res.RoomResDTO;
import com.cg.spblaguna.repository.IKindOfRoomRespository;
import com.cg.spblaguna.repository.IPerTypeRepository;
import com.cg.spblaguna.repository.IRateRespository;
import com.cg.spblaguna.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IKindOfRoomRespository kindOfRoomRespository;

    @Autowired
    private IPerTypeRepository perTypeRepository;

    @Autowired
    private IRateRespository rateRespository;

    public List<RoomResDTO> getRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(Room::toRoomResDto).collect(Collectors.toList());
    }

    public RoomResDTO save(RoomReqDTO roomReqDTO) {
        KindOfRoom kindOfRoom = kindOfRoomRespository.findById(roomReqDTO.getKingOfRoomId()).get();
        PerType perType = perTypeRepository.findById(roomReqDTO.getPerTypId()).get();
        Room room = roomRepository
                .save(new Room(roomReqDTO.getName(),roomReqDTO.getRoomType()
                        ,roomReqDTO.getStatusRoom(),
                        roomReqDTO.getViewType(),roomReqDTO.getPricePerNight()
                        ,roomReqDTO.getAcreage(),
                        roomReqDTO.getSleep(),roomReqDTO.getDescription(),
                        roomReqDTO.getUtilitie(),kindOfRoom,perType));
        return room.toRoomResDto();
    }

    public RoomResDTO update(RoomReqDTO roomReqDTO){
        KindOfRoom kindOfRoom = kindOfRoomRespository.findById(roomReqDTO.getKingOfRoomId()).get();
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
}
