package com.cg.spblaguna.repository;

import com.cg.spblaguna.model.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReceptionistRepository  extends JpaRepository<Receptionist, Long> {
    List<Receptionist> findAllByUser_Unlock (boolean user_unlock);
    Receptionist findByUser_Id(Long id);
}
