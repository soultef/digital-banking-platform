package com.bank.auth_service.service;

import com.bank.auth_service.dto.RegistrationRequestDto;
import com.bank.auth_service.dto.UserRegistrationResponseDto;
import com.bank.auth_service.entities.User;
import com.bank.auth_service.exception.UserExistException;
import com.bank.auth_service.mapper.UserMapper;
import com.bank.auth_service.mapper.UserRegistrationResponseMapper;
import com.bank.auth_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserRegistrationResponseMapper responseMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper,
                       UserRegistrationResponseMapper responseMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.responseMapper = responseMapper;
    }


    @Transactional
    public UserRegistrationResponseDto registerUser(RegistrationRequestDto requestDto)
    {
        String email = requestDto.getEmail();
        boolean userExists = userRepository.existsByEmail(email);
        if(userExists)
            throw new UserExistException(email);

        User userEntity = userMapper.toEntity(requestDto);
        userRepository.save(userEntity);

        UserRegistrationResponseDto dto = responseMapper.toDto(userEntity);
        dto.setMessage("You successfully registered");

        return dto;

    }


}
