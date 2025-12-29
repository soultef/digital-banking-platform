package com.bank.auth_service.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RegistrationRequestDtoValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    private AddressRequestDto validAddressDto()
    {
        AddressRequestDto validAddress = new AddressRequestDto();
        validAddress.setStreetNumber("123");
        validAddress.setStreetName("Main St");
        validAddress.setUnitNumber("100");
        validAddress.setCity("Dallas");
        validAddress.setZipcode("75000");
        validAddress.setCounty("Dallas");
        validAddress.setCountry("U.S.a");
        return validAddress;
    }
    private RegistrationRequestDto validRegistrationDto() {
        RegistrationRequestDto dto = new RegistrationRequestDto();
        dto.setFirstName("Mamo");
        dto.setLastName("Wolde");
        dto.setEmail("mamo@xyz.com");
        dto.setPhone("+12345678901");
        dto.setBirthDate(LocalDate.of(2000, 1, 1));
        dto.setAddresses(List.of(new AddressRequestDto()));
        return dto;
    }

    @Test
    void shouldPassWhenAllFieldsAreValid() {
        Set<ConstraintViolation<RegistrationRequestDto>> violations =
                validator.validate(validRegistrationDto());

        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailWhenFirstNameIsBlank() {
        RegistrationRequestDto dto = validRegistrationDto();
        dto.setFirstName("");

        Set<ConstraintViolation<RegistrationRequestDto>> violations =
                validator.validate(dto);

        assertThat(violations)
                .anyMatch(v -> v.getPropertyPath().toString().equals("firstName"));
    }

    @Test
    void shouldFailWhenEmailIsInvalid() {
        RegistrationRequestDto dto = validRegistrationDto();
        dto.setEmail("invalid-email");

        Set<ConstraintViolation<RegistrationRequestDto>> violations =
                validator.validate(dto);

        assertThat(violations)
                .anyMatch(v -> v.getPropertyPath().toString().equals("email"));
    }

    @Test
    void shouldFailWhenBirthDateIsInFuture() {
        RegistrationRequestDto dto = validRegistrationDto();
        dto.setBirthDate(LocalDate.now().plusDays(1));

        Set<ConstraintViolation<RegistrationRequestDto>> violations =
                validator.validate(dto);

        assertThat(violations)
                .anyMatch(v -> v.getPropertyPath().toString().equals("birthDate"));
    }

    @Test
    void shouldFailWhenAddressesAreEmpty() {
        RegistrationRequestDto dto = validRegistrationDto();
        dto.setAddresses(List.of());

        Set<ConstraintViolation<RegistrationRequestDto>> violations =
                validator.validate(dto);

        assertThat(violations)
                .anyMatch(v -> v.getPropertyPath().toString().equals("addresses"));
    }
}
