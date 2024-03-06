package com.cg.spblaguna.controller.api;

import com.cg.spblaguna.model.Customer;
import com.cg.spblaguna.model.User;
import com.cg.spblaguna.model.dto.req.LockStatusReqDTO;
import com.cg.spblaguna.model.dto.res.CustomerResDTO;
import com.cg.spblaguna.service.customer.ICustomerService;
import com.cg.spblaguna.service.user.UserService;
import com.cg.spblaguna.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerAPI {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private UserService userService;

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
    public ResponseEntity<?> updateCustomer(@PathVariable Long userId, @RequestBody CustomerResDTO customerResDTO) {
        Customer customer = customerService.findByUser_Id(userId);
        customer.setDob(customerResDTO.getDob());
        customer.setEmail(customerResDTO.getEmail());
        customer.setFirstName(customerResDTO.getFirstName());
        customer.setLastName(customerResDTO.getLastName());
        customer.setPhone(customerResDTO.getPhone());
        customer.setAddress(customerResDTO.getAddress());
        customer.setStatusUser(customerResDTO.getStatusUser());
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/lock/{id}")
    public ResponseEntity<?> changeLock(@PathVariable Long id, @RequestBody LockStatusReqDTO lockStatusReqDTO) {
        User user = userService.findById(lockStatusReqDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + lockStatusReqDTO.getUserId()));
        user.setUnlock(false);
        userService.save(user);
        Customer customer = customerService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customer.setUser(user);
        customerService.save(customer);
        return ResponseEntity.ok().build();
    }


}
