package com.bank.auth_service.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class AddressRequestDtoValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        try (ValidatorFactory factory =
                     Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    private AddressRequestDto validAddress() {
        AddressRequestDto dto = new AddressRequestDto();
        dto.setStreetNumber("123");
        dto.setStreetName("Main St");
        dto.setCity("Dallas");
        dto.setState("TX");
        dto.setZipcode("12345");
        dto.setCountry("USA");
        dto.setCounty("Dallas");
        dto.setUnitNumber("A");
        return dto;
    }

    @Test
    void shouldFailWhenStreetNumberIsBlank() {
        AddressRequestDto dto = validAddress();
        dto.setStreetNumber("");

        var violations = validator.validate(dto);

        assertThat(violations)
                .anyMatch(v -> v.getPropertyPath().toString().equals("streetNumber"));
    }

    @Test
    void shouldPassWhenAddressIsValid() {
        var violations = validator.validate(validAddress());
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailWhenZipcodeIsInvalid()
    {
        AddressRequestDto dto = validAddress();
        dto.setZipcode("123");

        var violations = validator.validate(dto);
        assertThat(violations).anyMatch(v ->v.getPropertyPath().toString().equals("zipcode"));
    }

    @Test
    void shouldFailWhenStateIsInvalid()
    {
        AddressRequestDto dto = validAddress();
        dto.setState("Texas");

        var violations = validator.validate(dto);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("state"));
    }

    @Test
    void shouldPassWhenUnitNumberIsNull()
    {
        AddressRequestDto dto = validAddress();
        dto.setUnitNumber(null);

        var nullUnitNumber = validator.validate(dto);
        assertThat(nullUnitNumber).isEmpty();
    }
}

