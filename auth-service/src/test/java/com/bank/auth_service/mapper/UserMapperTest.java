package com.bank.auth_service.mapper;

import com.bank.auth_service.dto.AddressRequestDto;
import com.bank.auth_service.dto.RegistrationRequestDto;
import com.bank.auth_service.entities.User;
import com.bank.auth_service.entities.UserAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    void shouldMapDtoToEntityWithFullAddress() {

        RegistrationRequestDto dto = new RegistrationRequestDto();
        dto.setFirstName("Mamo");
        dto.setLastName("Wolde");
        dto.setEmail("mamo@xyz.com");
        dto.setPhone("+12345678901");
        dto.setBirthDate(LocalDate.of(2000, 1, 1));

        AddressRequestDto addressDto = new AddressRequestDto();
        addressDto.setStreetNumber("123");
        addressDto.setStreetName("Main St");
        addressDto.setCity("Addis Ababa");
        addressDto.setState("AA");
        addressDto.setZipcode("1000");
        addressDto.setCountry("Ethiopia");
        addressDto.setCounty("Central");

        dto.setAddresses(List.of(addressDto));

        // When
        User user = userMapper.toEntity(dto);

        // Then
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Mamo");
        assertThat(user.getLastName()).isEqualTo("Wolde");
        assertThat(user.getEmail()).isEqualTo("mamo@xyz.com");
        assertThat(user.getPhone()).isEqualTo("+12345678901");
        assertThat(user.getBirthDate()).isEqualTo(LocalDate.of(2000, 1, 1));

        assertThat(user.getAddresses()).hasSize(1);
        UserAddress mappedAddress = user.getAddresses().get(0);
        assertThat(mappedAddress.getStreetNumber()).isEqualTo("123");
        assertThat(mappedAddress.getStreetName()).isEqualTo("Main St");
        assertThat(mappedAddress.getCity()).isEqualTo("Addis Ababa");
        assertThat(mappedAddress.getState()).isEqualTo("AA");
        assertThat(mappedAddress.getZipCode()).isEqualTo("1000");
        assertThat(mappedAddress.getCountry()).isEqualTo("Ethiopia");
        assertThat(mappedAddress.getCounty()).isEqualTo("Central");

        // Check back-reference
        assertThat(mappedAddress.getUser()).isEqualTo(user);
    }

    @Test
    void shouldMapEntityToDtoWithFullAddress() {
        // Given
        User user = new User();
        user.setFirstName("Mamo");
        user.setLastName("Wolde");
        user.setEmail("mamo@xyz.com");
        user.setPhone("+12345678901");
        user.setBirthDate(LocalDate.of(2000, 1, 1));

        UserAddress address = new UserAddress();
        address.setStreetNumber("123");
        address.setStreetName("Main St");
        address.setCity("Addis Ababa");
        address.setState("AA");
        address.setZipCode("1000");
        address.setCountry("Ethiopia");
        address.setCounty("Central");
        address.setUser(user);

        user.setAddresses(List.of(address));

        // When
        RegistrationRequestDto dto = userMapper.toDto(user);

        // Then
        assertThat(dto).isNotNull();
        assertThat(dto.getFirstName()).isEqualTo("Mamo");
        assertThat(dto.getLastName()).isEqualTo("Wolde");

        assertThat(dto.getAddresses()).hasSize(1);
        AddressRequestDto mappedAddress = dto.getAddresses().get(0);
        assertThat(mappedAddress.getStreetNumber()).isEqualTo("123");
        assertThat(mappedAddress.getStreetName()).isEqualTo("Main St");
        assertThat(mappedAddress.getCity()).isEqualTo("Addis Ababa");
        assertThat(mappedAddress.getState()).isEqualTo("AA");
        assertThat(mappedAddress.getZipcode()).isEqualTo("1000");
        assertThat(mappedAddress.getCountry()).isEqualTo("Ethiopia");
        assertThat(mappedAddress.getCounty()).isEqualTo("Central");
    }

    @Test
    void shouldHandleNullAndEmptyAddresses() {
        RegistrationRequestDto dto = new RegistrationRequestDto();
        dto.setFirstName("Mamo");

        User user = userMapper.toEntity(dto);
        assertThat(user.getAddresses()).isNull();

        dto.setAddresses(List.of());
        user = userMapper.toEntity(dto);
        assertThat(user.getAddresses()).isEmpty();
    }
}
