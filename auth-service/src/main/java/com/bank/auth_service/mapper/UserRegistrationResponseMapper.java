package com.bank.auth_service.mapper;

import com.bank.auth_service.dto.UserRegistrationResponseDto;
import com.bank.auth_service.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRegistrationResponseMapper {

    @Mapping(target = "email", source = "email")
    public UserRegistrationResponseDto toDto(User user);
}
