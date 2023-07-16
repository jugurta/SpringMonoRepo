package com.jai.core.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
@Data
@AllArgsConstructor
@Builder
public class AddressEntity {

    @Id
    Long id;
    String type;
    String name;
    String cityCode;
    Integer population;
}
