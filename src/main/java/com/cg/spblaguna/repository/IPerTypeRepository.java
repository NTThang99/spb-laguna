package com.cg.spblaguna.repository;

import com.cg.spblaguna.model.PerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPerTypeRepository extends JpaRepository<PerType, Long> {
}
