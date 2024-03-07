package com.cg.spblaguna.service.kindOfRoom;

import com.cg.spblaguna.model.KindOfRoom;
import com.cg.spblaguna.repository.IKindOfRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KindOfRoomService implements IKindOfRoomService {
    @Autowired
    private IKindOfRoomRepository kindOfRoomRepository;

    @Override
    public List<KindOfRoom> findAll() {
        return kindOfRoomRepository.findAll();
    }

    @Override
    public Optional<KindOfRoom> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(KindOfRoom kindOfRoom) {

    }

    @Override
    public void deleteById(Long id) {

    }
}