package com.jai.core.infrastructure.database.mongo.mapper;

import com.jai.core.domain.model.Address;
import com.jai.core.infrastructure.database.entity.AddressEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AddressMapper {

    Address toDomain(AddressEntity addressEntity);
    AddressEntity toEntity(Address address);
}
