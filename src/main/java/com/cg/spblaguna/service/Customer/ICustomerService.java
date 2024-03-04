package com.cg.spblaguna.service.Customer;

import com.cg.spblaguna.model.Customer;
import com.cg.spblaguna.model.dto.req.CustomerReqDTO;
import com.cg.spblaguna.model.dto.req.EmailReqDTO;
import com.cg.spblaguna.model.dto.req.ForgotPassword;
import com.cg.spblaguna.service.IGeneralService;

import java.util.Optional;
import java.util.List;

public interface ICustomerService extends IGeneralService<Customer,Long> {
    void register(CustomerReqDTO customerReqDTO);
    Optional<Customer> findCustomerByEmail(String email);
    boolean confirmEmail(EmailReqDTO emailReqDTO);
    boolean forgotPassword(ForgotPassword forgotPassword);

    List<Customer> findAllByUser_Unlock(boolean user_unlock);

    Customer findByUser_Id(Long id);
}
