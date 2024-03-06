package com.cg.spblaguna.controller.api;

import com.cg.spblaguna.model.Receptionist;
import com.cg.spblaguna.model.User;
import com.cg.spblaguna.model.dto.req.LockStatusReqDTO;
import com.cg.spblaguna.model.dto.req.ReceptionistReqDTO;
import com.cg.spblaguna.model.dto.req.RoomReqDTO;
import com.cg.spblaguna.model.enumeration.ELockStatus;
import com.cg.spblaguna.service.image.ImageService;
import com.cg.spblaguna.service.receptionist.ReceptionistService;
import com.cg.spblaguna.service.user.IUserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receptionists")
@CrossOrigin(origins = "*")
public class ReceptionistAPI {
    @Autowired
    private ReceptionistService receptionistService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ImageAPI imageAPI;

    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<?> getReceptionists(){
        List<Receptionist> receptionistList = receptionistService.findAllByUser_Unlock(true);
        return new ResponseEntity<>(receptionistList, HttpStatus.OK);
    }
    @GetMapping("/search")
    public List<Receptionist> getReceptionistsWithFilters(@RequestParam(value = "receptionistName", required = false) String receptionistName) {
        return receptionistService.findReceptionistsWithFilters(receptionistName);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getReceptionistById(@PathVariable Long id) {
        Receptionist receptionist = receptionistService.findById(id).get();
        return new ResponseEntity<>(receptionist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Receptionist deleteReceptionist = receptionistService.findById(id).get();
        receptionistService.delete(deleteReceptionist);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/lock/{id}")
    public ResponseEntity<?> lockUser(@PathVariable Long id){
        Receptionist receptionist = receptionistService.findByUser_Id(id);
        receptionist.setLockStatus(ELockStatus.LOCK);
        receptionistService.save(receptionist);
        return new ResponseEntity<>(HttpStatus.OK);


    }

    @PostMapping
    public ResponseEntity<?> createReceptionist(@RequestBody ReceptionistReqDTO receptionistReqDTO) {
        receptionistService.create(receptionistReqDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
