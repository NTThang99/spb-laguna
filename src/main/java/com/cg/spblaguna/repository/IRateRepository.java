package com.cg.spblaguna.repository;

import com.cg.spblaguna.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRateRepository extends JpaRepository<Rate,Long> {
}
