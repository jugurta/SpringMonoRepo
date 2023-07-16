package com.jai.core.infrastructure.database.adapter;

import com.mongodb.reactivestreams.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import com.jai.core.domain.model.Address;
import com.jai.core.infrastructure.database.mongo.adapter.AddressRepositoryAdapter;
import com.jai.core.infrastructure.database.mongo.mapper.AddressMapper;
import com.jai.core.infrastructure.database.mongo.mapper.AddressMapperImpl;
import com.jai.core.providers.AddressProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class AddressRepositoryAdapterTest {

    static final String CONNECTION_STRING = "mongodb://%s:%d";
    MongodExecutable mongodExecutable;
    ReactiveMongoTemplate reactiveMongoTemplate;
    AddressRepositoryAdapter addressRepositoryAdapter;
    AddressMapper addressMapper;

    @BeforeEach
    void setUp() throws Exception {
        String ip = "localhost";
        int port = 27019;
        ImmutableMongodConfig mongodConfig = MongodConfig
                .builder()
                .version(Version.Main.V6_0)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();
        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        reactiveMongoTemplate = new ReactiveMongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "mongoDB");
        addressMapper = new AddressMapperImpl();
        addressRepositoryAdapter = new AddressRepositoryAdapter(reactiveMongoTemplate, addressMapper);
    }

    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }


    @ParameterizedTest
    @ArgumentsSource(AddressProvider.class)
    void givenInsertedAddressIndatabaseShouldReturnSame(Address address) {
        //GIVEN
        StepVerifier.create(addressRepositoryAdapter.insertAddress(address)).expectNextCount(1L).verifyComplete();
        //WHEN
        Mono<Address> result = addressRepositoryAdapter.getAdressById(address.getId());
        //THEN
        StepVerifier.create(result).expectNext(address).verifyComplete();

    }
}
