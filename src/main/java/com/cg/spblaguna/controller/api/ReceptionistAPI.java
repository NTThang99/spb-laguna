package com.cg.spblaguna.controller.api;

import com.cg.spblaguna.model.Receptionist;
import com.cg.spblaguna.service.receptionist.ReceptionistService;
import com.cg.spblaguna.service.user.IUserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<?> getReceptionists(){
        List<Receptionist> receptionistList = receptionistService.findAllByUser_Unlock(true);
        return new ResponseEntity<>(receptionistList, HttpStatus.OK);
    }
    @GetMapping("/search")
    public List<Receptionist> getReceptionistsWithFilters(@RequestParam(value = "receptionistName", required = false) String receptionistName) {
        return receptionistService.findReceptionistsWithFilters(receptionistName);
    }

}
