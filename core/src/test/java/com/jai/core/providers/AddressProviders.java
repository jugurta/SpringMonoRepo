package com.jai.core.providers;

import org.junit.jupiter.params.provider.Arguments;


import java.util.stream.Stream;

public class AddressProviders {

    private static Stream<Arguments> provideEntityAndModel() {
        return Stream.of(Arguments.of(new AddressEntityProvider().addressEntity, new AddressProvider().address));
    }
}
