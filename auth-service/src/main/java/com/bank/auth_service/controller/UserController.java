package com.bank.auth_service.controller;

import com.bank.auth_service.dto.RegistrationRequestDto;
import com.bank.auth_service.dto.UserRegistrationResponseDto;
import com.bank.auth_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDto> registerUser(@Valid @RequestBody RegistrationRequestDto requestDto)
    {
        UserRegistrationResponseDto responseDto = userService.registerUser(requestDto);
        return ResponseEntity.status(201).body(responseDto);
    }

}
