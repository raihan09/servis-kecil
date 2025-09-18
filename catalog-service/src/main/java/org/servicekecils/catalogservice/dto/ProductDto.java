package org.servicekecils.catalogservice.dto;

import java.math.BigDecimal;

public record ProductDto(
        String sku,
        String name,
        BigDecimal price,
        Integer stock
) {}