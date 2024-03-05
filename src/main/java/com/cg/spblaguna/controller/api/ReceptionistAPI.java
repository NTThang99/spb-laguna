package com.cg.spblaguna.controller.api;

import com.cg.spblaguna.service.receptionist.ReceptionistService;
import com.cg.spblaguna.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/receptionists")
@CrossOrigin(origins = "*")
public class ReceptionistAPI {
    @Autowired
    private ReceptionistService receptionistService;
    @Autowired
    private IUserService userService;
}
