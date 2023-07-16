package com.jai.core.providers;

import com.jai.core.domain.model.Address;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class AddressProvider implements ArgumentsProvider {

    Address address = Address.builder()
            .id(1L)
            .name("name")
            .cityCode("15000")
            .population(1000)
            .build();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(Arguments.of(address));

    }
}
