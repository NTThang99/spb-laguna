package com.cg.spblaguna.controller.api;

import com.cg.spblaguna.model.Customer;
import com.cg.spblaguna.model.enumeration.ERole;
import com.cg.spblaguna.model.User;
import com.cg.spblaguna.model.dto.res.ChangePassword;
import com.cg.spblaguna.model.dto.res.JwtResponse;
import com.cg.spblaguna.model.dto.res.LoginDTO;
import com.cg.spblaguna.model.dto.req.CustomerReqDTO;
import com.cg.spblaguna.model.dto.req.EmailReqDTO;
import com.cg.spblaguna.model.dto.req.ForgotPassword;
import com.cg.spblaguna.repository.IUserRepository;
import com.cg.spblaguna.security.jwt.JwtUtils;
import com.cg.spblaguna.security.roomUser.RoomUserDetails;
import com.cg.spblaguna.service.customer.CustomerService;
import com.cg.spblaguna.service.user.UserService;
import com.cg.spblaguna.util.PasswordEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;


import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserAPI {
    @Autowired
    private UserService userService;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id) {
        User user = userService.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/userlogin/{storedUserId}")
    public ResponseEntity<?> getUserIDUser(@PathVariable Long storedUserId) {
        User user = userService.findById(storedUserId).orElse(null);
        if (user != null) {
            } else if (user.getRole() == ERole.CUSTOMER) {
                Customer customer = customerService.findByUser_Id(storedUserId);
                return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/finduser/{useremail}")
    public ResponseEntity<?> getUserByemail(@PathVariable String useremail) {
        User user = iUserRepository.findByEmail(useremail).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> Register(@RequestBody CustomerReqDTO customerReqDTO) {
        customerService.register(customerReqDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {
        User user = iUserRepository.findById(changePassword.getUserId()).get();
        user.setPassword(PasswordEncryptionUtil.encryptPassword(changePassword.getPassword()));
        iUserRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword forgotPassword) {
        boolean isConfirmed = customerService.forgotPassword(forgotPassword);
        if (isConfirmed) {
            User user = iUserRepository.findByEmail(forgotPassword.getEmail()).get();
            Long userId = user.getId();
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mã xác nhân không đúng");
        }
    }

    @PostMapping("email")
    public ResponseEntity<?> senderEmail(@RequestBody EmailReqDTO emailReqDTO) {
        boolean isConfirmed = customerService.confirmEmail(emailReqDTO);
        if (isConfirmed) {
            return ResponseEntity.ok("Gửi mail thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("email không tồn tại");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mật khẩu không đúng. Vui lòng nhập lại.");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtTokenForUser(authentication);
        RoomUserDetails roomUserDetails = (RoomUserDetails) authentication.getPrincipal();
        List<String> roles = roomUserDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        User user = userService.findById(roomUserDetails.getId()).get();
        if (user.isUnlock()) {
            JwtResponse jwtResponse = new JwtResponse(
                    roomUserDetails.getId(),
                    roomUserDetails.getEmail(),
                    jwt,
                    roles
            );
            ResponseCookie springCookie = ResponseCookie.from("JWT", jwt)
                    .httpOnly(false)
                    .secure(false)
                    .sameSite("None")
                    .path("/")
                    .maxAge(60 * 1000)
                    .domain(".localhost")
//                    .domain("192.168.1.64")
                    .build();
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                    .body(jwtResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tài khoản đã bị khóa.");
        }
    }

}
