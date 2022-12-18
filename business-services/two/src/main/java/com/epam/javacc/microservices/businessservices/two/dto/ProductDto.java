package com.epam.javacc.microservices.businessservices.two.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductDto {

    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private Double price;
}
