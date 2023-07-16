package com.jai.core.domain.repository;

import com.jai.core.domain.model.Address;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressRepository {

    Mono<Address> getAdressById(Long id);
    Flux<Address> getAllAdresses();
    Mono<Address> insertAddress(Address address);
}
