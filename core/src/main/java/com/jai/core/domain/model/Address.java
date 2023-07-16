package com.jai.core.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Address {
    Long id;
    String type;
    String name;
    String cityCode;
    Integer population;
}
