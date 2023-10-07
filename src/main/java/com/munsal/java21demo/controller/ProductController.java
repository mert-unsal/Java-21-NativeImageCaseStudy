package com.munsal.java21demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.munsal.java21demo.domain.jsonView.ViewRole;
import com.munsal.java21demo.domain.model.ProductDto;
import com.munsal.java21demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ProductController.BASE_PATH)
@RequiredArgsConstructor
public class ProductController {
    public static final String BASE_PATH = "product";
    private static final String TAG = "Product Controllers";
    private final ProductService productService;

    @GetMapping("all")
    @Operation(tags = TAG, summary = "Get All Products")
    @JsonView({ViewRole.ViewRequest.class})
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(tags = TAG, summary = "Get Product By Id")
    @JsonView({ViewRole.ViewRequest.class})
    public ProductDto get(@PathVariable("id") Long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping
    @Operation(tags = TAG, summary = "Add a new Product")
    @JsonView({ViewRole.AddRequest.class})
    public void add(@RequestBody @Valid ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @PutMapping("{id}")
    @Operation(tags = TAG, summary = "Update Product")
    @JsonView({ViewRole.UpsertRequest.class})
    public void update(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
        productService.updateProduct(id, productDto);
    }

    @DeleteMapping("{id}")
    @Operation(tags = TAG, summary = "Delete Product")
    @JsonView({ViewRole.DeleteRequest.class})
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
