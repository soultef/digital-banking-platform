package com.bank.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddressRequestDto {

    @NotBlank(message = "Street number is required")
    @Size(max = 10, message = "Street number must be at most 10 characters")
    private String streetNumber;

    @NotBlank(message = "Street name is required")
    @Size(max = 50, message = "Street name must be at most 50 characters")
    private String streetName;

    @Size(max = 10, message = "Unit number must be at most 10 characters")
    private String unitNumber;

    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City must be at most 50 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "State must be a valid 2-letter code")
    private String state;

    @NotBlank(message = "Zipcode is required")
    @Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "Zipcode must be valid US ZIP format")
    private String zipcode;

    @Size(max = 50, message = "County must be at most 50 characters")
    private String county;

    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country must be at most 50 characters")
    private String country;


    // Getters and Setters
    public String getStreetNumber() { return streetNumber; }
    public void setStreetNumber(String streetNumber) { this.streetNumber = streetNumber; }

    public String getStreetName() { return streetName; }
    public void setStreetName(String streetName) { this.streetName = streetName; }

    public String getUnitNumber() { return unitNumber; }
    public void setUnitNumber(String unitNumber) { this.unitNumber = unitNumber; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public String getCounty() { return county; }
    public void setCounty(String county) { this.county = county; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
