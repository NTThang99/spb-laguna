package com.cg.spblaguna.service.Customer;

import com.cg.spblaguna.model.Customer;
import com.cg.spblaguna.model.ERole;
import com.cg.spblaguna.model.User;
import com.cg.spblaguna.model.dto.req.CustomerReqDTO;
import com.cg.spblaguna.model.dto.req.EmailReqDTO;
import com.cg.spblaguna.model.dto.req.ForgotPassword;
import com.cg.spblaguna.repository.ICustomerRepository;
import com.cg.spblaguna.repository.IUserRepository;
import com.cg.spblaguna.util.EmailUtil;
import com.cg.spblaguna.util.PasswordEncryptionUtil;
import com.cg.spblaguna.util.RandomCode;
import com.cg.spblaguna.util.SendEmail;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private IUserRepository iUserRepository;


    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return iCustomerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        iCustomerRepository.deleteById(id);
    }

    @Override
    public void register(CustomerReqDTO customerReqDTO) {
        User user = new User();
        user.setEmail(customerReqDTO.getEmail());
        user.setPassword(PasswordEncryptionUtil.encryptPassword(customerReqDTO.getPassword()));
        user.setRole(ERole.CUSTOMER);
        iUserRepository.save(user);

        Customer customer = new Customer();
        customer.setDob(customerReqDTO.getDob());
        customer.setEmail(customerReqDTO.getEmail());
        customer.setFirstName(customerReqDTO.getFirstName());
        customer.setLastName(customerReqDTO.getLastName());
        customer.setPhone(customerReqDTO.getPhone());
        customer.setAddress(customerReqDTO.getAddress());
        customer.setStatusUser(customerReqDTO.getStatusUser());
        customer.setPassword(customerReqDTO.getPassword());
        iCustomerRepository.save(customer);


    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public boolean confirmEmail(EmailReqDTO emailReqDTO) {
        User user = iUserRepository.findByEmail(emailReqDTO.getEmail()).get();
        if (user != null) {
            String email = emailReqDTO.getEmail();
            String title = "Yêu cầu đặt lại mật khẩu";
            String code = RandomCode.generateRandomCode(6);
            user.setCode(code);
            iUserRepository.save(user);
            String body = SendEmail.EmailResetPassword(user.getEmail(), code);
            emailUtil.sendEmail(email, title, body);
            return true;
        } else {
            return false;
        }
    }



    @Override
    public boolean forgotPassword(ForgotPassword forgotPassword) {
        User user = iUserRepository.findByEmail(forgotPassword.getEmail()).get();
        return user != null && (forgotPassword.getCode()).equals(user.getCode());
    }

    @Override
    public List<Customer> findAllByUser_Unlock(boolean user_unlock) {
       return iCustomerRepository.findAllByUser_Unlock(user_unlock);
    }

    @Override
    public Customer findByUser_Id(Long id) {
        return iCustomerRepository.findByUser_Id(id);
    }
}
