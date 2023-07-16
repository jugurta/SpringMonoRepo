package com.jai.core.infrastructure.database.mapper;


import com.jai.core.infrastructure.database.entity.AddressEntity;
import com.jai.core.domain.model.Address;
import com.jai.core.infrastructure.database.mongo.mapper.AddressMapper;
import com.jai.core.infrastructure.database.mongo.mapper.AddressMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;


class AddressMapperTest {

    AddressMapper addressMapper;


    @BeforeEach
    void setUp() {
        addressMapper = new AddressMapperImpl();
    }


    @ParameterizedTest
    @MethodSource("com.jai.core.providers.AddressProviders#provideEntityAndModel")
    void givenAddressEntityShouldReturnAddressDomain(AddressEntity addressEntity, Address address) {
        assertThat(addressMapper.toDomain(addressEntity)).isEqualTo(address);
    }

    @ParameterizedTest
    @MethodSource("com.jai.core.providers.AddressProviders#provideEntityAndModel")
    void givenAddressDomainShouldReturnAddressEntity(AddressEntity addressEntity, Address address) {
        assertThat(addressMapper.toEntity(address)).isEqualTo(addressEntity);
    }
}
