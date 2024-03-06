package com.cg.spblaguna.controller.api;

import com.cg.spblaguna.model.dto.res.EBookStatusResDTO;
import com.cg.spblaguna.model.dto.res.EDepositedStatusResDTO;
import com.cg.spblaguna.model.enumeration.EBookStatus;
import com.cg.spblaguna.model.enumeration.EDepositedStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ebookstatus")
@CrossOrigin(origins = "*")
public class EBookStatusAPI {
    @GetMapping
    public ResponseEntity<?> getAllEBookStatuses() {
        EBookStatus[] eBookStatuses = EBookStatus.values();
        List<EBookStatusResDTO> eBookStatusResDTOS = Arrays.stream(eBookStatuses)
                .map(eBookStatus -> new EBookStatusResDTO(eBookStatus.toString(), eBookStatus.getName()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(eBookStatusResDTOS, HttpStatus.OK);
    }
}
