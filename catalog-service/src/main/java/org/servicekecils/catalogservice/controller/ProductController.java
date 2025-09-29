package org.servicekecils.catalogservice.controller;

import lombok.RequiredArgsConstructor;
import org.servicekecils.catalogservice.dto.ProductDto;
import org.servicekecils.catalogservice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/")
    public List<ProductDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{sku}")
    public ProductDto bySku(@PathVariable String sku) {
        return service.getBySku(sku);
    }

    @GetMapping
    public Page<ProductDto> search(
            @RequestParam(required = false) String q,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return service.search(q, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody ProductDto dto) {
        return service.create(dto);
    }
}
