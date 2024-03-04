package com.cg.spblaguna.controller;

import com.cg.spblaguna.model.dto.req.RoomReqDTO;
import com.cg.spblaguna.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/rooms")


public class RoomRestController {
    @Autowired
    private RoomService roomService;

    @GetMapping("")
    public ResponseEntity<?> showRooms() {
        return new ResponseEntity<>(roomService.getRooms(), HttpStatus.OK);
    }

    @PostMapping
//    @PreAuthorize("hasAnyRole('MODIFIER')")
    public ResponseEntity<?> saveRoom(@Validated @RequestBody RoomReqDTO roomReqDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getValidationErrorJson(bindingResult));
        }
        return new ResponseEntity<>(roomService.save(roomReqDTO), HttpStatus.OK);
    }

    private Map<String, String> getValidationErrorJson(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyRole('MODIFIER')")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody RoomReqDTO roomReqDTO) {
        roomReqDTO.setId(id);
        return new ResponseEntity<>(roomService.update(roomReqDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('MODIFIER', 'ADMIN')")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        roomService.delete(id);
        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);
    }

}
