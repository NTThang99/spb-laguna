package com.cg.spblaguna.repository;

import com.cg.spblaguna.model.Customer;
import com.cg.spblaguna.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByEmail(String email);
    List<Customer> findAllByUser_Unlock(boolean user_unlock);
    Customer findByUser_Id(Long id);
}
