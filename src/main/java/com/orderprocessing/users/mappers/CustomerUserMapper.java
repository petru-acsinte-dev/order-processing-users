package com.orderprocessing.users.mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.orderprocessing.common.mappers.GlobalMapperConfig;
import com.orderprocessing.users.dto.CreateCustomerUserRequest;
import com.orderprocessing.users.dto.CustomerUserResponse;
import com.orderprocessing.users.dto.UpdateCustomerUserRequest;
import com.orderprocessing.users.entities.CustomerUser;

@Mapper(config = GlobalMapperConfig.class, uses = AddressMapper.class)
public interface CustomerUserMapper {

	@Mapping(source = "externalId", 	target = "externalId")
    @Mapping(source = "role.role", 		target = "role")
    @Mapping(source = "status.status", 	target = "status")
    CustomerUserResponse toResponse(CustomerUser entity);

	@Mapping(target = "id", 		ignore = true)
    @Mapping(target = "externalId", ignore = true)
    @Mapping(target = "created", 	ignore = true)
    @Mapping(target = "role", 		ignore = true)
    @Mapping(target = "status", 	ignore = true)
	CustomerUser fromCreateRequest(CreateCustomerUserRequest request);

	@Mapping(target = "id", 		ignore = true)
	@Mapping(target = "username", 	ignore = true)
    @Mapping(target = "externalId", ignore = true)
    @Mapping(target = "created", 	ignore = true)
    @Mapping(target = "role", 		ignore = true)
    @Mapping(target = "status", 	ignore = true)
	void updateRequest(UpdateCustomerUserRequest request,
					@MappingTarget CustomerUser customerUser);

	default OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atOffset(ZoneOffset.UTC);
    }
}
