package com.bank.auth_service.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class RegistrationRequestDto {

    @NotBlank(message = "First name should not be blank")
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstName;

    @NotBlank(message = "Last name should not be blank")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid")
    private String phone;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;


    @NotEmpty(message = "At least one address is required")
    private List<AddressRequestDto> addresses;

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public List<AddressRequestDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressRequestDto> addresses) {
        this.addresses = addresses;
    }
}
