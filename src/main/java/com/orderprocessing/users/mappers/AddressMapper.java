package com.orderprocessing.users.mappers;

import org.mapstruct.Mapper;

import com.orderprocessing.common.mappers.GlobalMapperConfig;
import com.orderprocessing.users.dto.AddressDTO;
import com.orderprocessing.users.entities.Address;

@Mapper(config = GlobalMapperConfig.class)
public interface AddressMapper {

	Address toEntity(AddressDTO dto);

	AddressDTO toDTO(Address entity);

}
