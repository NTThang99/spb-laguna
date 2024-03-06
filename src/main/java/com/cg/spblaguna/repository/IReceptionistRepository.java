package com.cg.spblaguna.repository;

import com.cg.spblaguna.model.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReceptionistRepository  extends JpaRepository<Receptionist, Long> {
    List<Receptionist> findAllByUser_Unlock (boolean user_unlock);
    Receptionist findByUser_Id(Long id);
    @Query("SELECT r FROM Receptionist r " +
            "WHERE (:receptionistName IS NULL OR LOWER(r.receptionistName) LIKE LOWER(CONCAT('%', :receptionistName, '%')))" +
            "  AND r.user.unlock = true")
    List<Receptionist> findReceptionistsWithFilters(@Param("receptionistName") String receptionistName);
}
