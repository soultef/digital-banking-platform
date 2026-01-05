package com.bank.auth_service.mapper;


import com.bank.auth_service.dto.UserRegistrationResponseDto;
import com.bank.auth_service.entities.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserRegistrationResponseMapperTest {

    @Test
    public void shouldMapEntityToDtoWithEmail()
    {

        User user = new User();
        user.setEmail("mamo@xyz.com");

        UserRegistrationResponseMapper mapper = Mappers.getMapper(UserRegistrationResponseMapper.class);
        UserRegistrationResponseDto dto = mapper.toDto(user);

        assertEquals("mamo@xyz.com", dto.getEmail());
        assertNull(dto.getMessage());
    }

}
