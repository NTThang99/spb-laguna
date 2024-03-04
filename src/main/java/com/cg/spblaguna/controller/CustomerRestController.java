package com.cg.spblaguna.controller;

import com.cg.spblaguna.model.Customer;
import com.cg.spblaguna.model.User;
import com.cg.spblaguna.model.dto.LockStatusReqDTO;
import com.cg.spblaguna.model.dto.UpdateCustomer;
import com.cg.spblaguna.model.dto.req.CustomerReqDTO;
import com.cg.spblaguna.service.Customer.CustomerService;
import com.cg.spblaguna.service.Customer.ICustomerService;
import com.cg.spblaguna.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerRestController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private UserService userService;



//Viet API customer
    //nnnn
    //mmm
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Customer> customerList = customerService.findAllByUser_Unlock(true);
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long userId) {
        Customer customer = customerService.findByUser_Id(userId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getCustomerByIdCus(@PathVariable Long userId) {
        Customer customer = customerService.findByUser_Id(userId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/getCustomer/{idCustomer}")
    public ResponseEntity<?> getCustomerByIdCustomer(@PathVariable Long idCustomer) {
        Customer customer = customerService.findById(idCustomer).get();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long userId, @RequestBody UpdateCustomer updateCustomer) {
        Customer customer = customerService.findByUser_Id(userId);
        customer.setDob(updateCustomer.getDob());
        customer.setEmail(updateCustomer.getEmail());
        customer.setFirstName(updateCustomer.getFirstName());
        customer.setLastName(updateCustomer.getLastName());
        customer.setPhone(updateCustomer.getPhone());
        customer.setAddress(updateCustomer.getAddress());
        customer.setStatusUser(updateCustomer.getStatusUser());
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @PutMapping("/lock/{id}")
//    public ResponseEntity<?> ChangeLock(@PathVariable Long id, @RequestBody LockStatusReqDTO lockStatusReqDTO) {
//        User user = userService.findById(lockStatusReqDTO.getUserId()).get();
//        user.setUnlock(false);
//        userService.save(user);
//        Customer customer = customerService.findById(id).get();
//        customer.setUser(user);
//        customerService.save(customer);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
