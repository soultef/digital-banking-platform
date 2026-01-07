package com.bank.auth_service.mapper;

import com.bank.auth_service.dto.AddressRequestDto;
import com.bank.auth_service.dto.RegistrationRequestDto;
import com.bank.auth_service.entities.User;
import com.bank.auth_service.entities.UserAddress;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    // -------------------------------
    // DTO -> Entity
    // -------------------------------
    @Mapping(target = "addresses", source = "addresses")
    User toEntity(RegistrationRequestDto dto);

    // Nested mapping for addresses
    default List<UserAddress> mapAddresses(List<AddressRequestDto> dtos, @Context User user) {
        if (dtos == null) return null;
        return dtos.stream()
                .map(dto -> {
                    UserAddress address = new UserAddress();
                    address.setStreetNumber(dto.getStreetNumber());
                    address.setStreetName(dto.getStreetName());
                    address.setCity(dto.getCity());
                    address.setState(dto.getState());
                    address.setZipCode(dto.getZipcode());
                    address.setCountry(dto.getCountry());
                    address.setCounty(dto.getCounty());
                    address.setUnit(dto.getUnitNumber());
                    address.setUser(user); // set back-reference
                    return address;
                })
                .toList();
    }

    @AfterMapping
    default void linkAddresses(@MappingTarget User user, RegistrationRequestDto dto) {
        if (dto.getAddresses() != null) {
            List<UserAddress> addresses = mapAddresses(dto.getAddresses(), user);
            user.setAddresses(addresses);
        }
    }

    // -------------------------------
    // Entity -> DTO
    // -------------------------------
    RegistrationRequestDto toResponseDto(User user);

    @Mapping(target = "addresses", source = "addresses")
    RegistrationRequestDto toDto(User user);

    // Nested mapping for addresses DTO
    default List<AddressRequestDto> mapAddressesToDto(List<UserAddress> addresses) {
        if (addresses == null) return null;
        return addresses.stream()
                .map(addr -> {
                    AddressRequestDto dto = new AddressRequestDto();
                    dto.setStreetName(addr.getStreetName());
                    dto.setStreetNumber(addr.getStreetNumber());
                    dto.setCity(addr.getCity());
                    dto.setState(addr.getState());
                    dto.setZipcode(addr.getZipCode());
                    dto.setCountry(addr.getCountry());
                    dto.setCounty(addr.getCounty());
                    dto.setUnitNumber(addr.getUnit());
                    return dto;
                })
                .toList();
    }
}

