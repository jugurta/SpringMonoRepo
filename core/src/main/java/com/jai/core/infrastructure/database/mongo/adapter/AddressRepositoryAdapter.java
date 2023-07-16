package com.jai.core.infrastructure.database.mongo.adapter;

import com.jai.core.domain.model.Address;
import com.jai.core.domain.repository.AddressRepository;
import com.jai.core.infrastructure.database.entity.AddressEntity;
import com.jai.core.infrastructure.database.mongo.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class AddressRepositoryAdapter implements AddressRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final AddressMapper addressMapper;

    public Mono<Address> getAdressById(Long id) {
        return reactiveMongoTemplate.findById(id, AddressEntity.class).map(addressMapper::toDomain);
    }

    public Flux<Address> getAllAdresses() {
        return reactiveMongoTemplate.findAll(AddressEntity.class).map(addressMapper::toDomain);
    }

    public Mono<Address> insertAddress(Address address) {
        return reactiveMongoTemplate.save(addressMapper.toEntity(address)).map(addressMapper::toDomain);
    }
}
