package org.servicekecils.catalogservice.service;

import lombok.RequiredArgsConstructor;
import org.servicekecils.catalogservice.dto.ProductDto;
import org.servicekecils.catalogservice.entity.Product;
import org.servicekecils.catalogservice.repository.ProductRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;

    @Transactional(readOnly = true)
    @Cacheable(value = "productBySku", key = "#sku")
    public ProductDto getBySku(String sku) {
        return repo.findBySku(sku)
                .map(this::toDto)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "products")
    public List<ProductDto> getAll() {
        return repo.findAll().stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public ProductDto create(ProductDto dto) {
        var product = Product.builder()
                .sku(dto.sku())
                .name(dto.name())
                .price(dto.price())
                .stock(dto.stock())
                .build();
        return toDto(repo.save(product));
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> search(String q, Pageable pageable) {
        return repo.findByNameContainingIgnoreCase(q, pageable).map(this::toDto);
    }

    private ProductDto toDto(Product p) {
        return new ProductDto(p.getSku(), p.getName(), p.getPrice(), p.getStock());
    }
}
