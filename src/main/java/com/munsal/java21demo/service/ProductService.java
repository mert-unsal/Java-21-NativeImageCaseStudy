package com.munsal.java21demo.service;

import com.munsal.java21demo.domain.entity.ProductEntity;
import com.munsal.java21demo.domain.model.ProductDto;
import com.munsal.java21demo.exception.DomainNotFoundException;
import com.munsal.java21demo.exception.InsufficientStockException;
import com.munsal.java21demo.factory.ProductFactory;
import com.munsal.java21demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.munsal.java21demo.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductFactory productFactory;

    @Transactional
    public ProductEntity getProductEntityByBookIdAndStock(Long bookId, Long stock) {
        return productRepository.findByBookEntity_IdAndStockGreaterThanEqual(bookId, stock).orElseThrow(() -> {
            throw new InsufficientStockException(PRODUCT_STOCK_QUERY_UNSUITABLE);
        });
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(productFactory::toProductDto).toList();
    }

    public ProductDto getProduct(Long productId) {
        return productRepository.findById(productId).map(productFactory::toProductDto).orElseThrow(() -> {
            throw new DomainNotFoundException(PRODUCT_COULD_NOT_FOUND, productId);
        });
    }

    public void addProduct(ProductDto bookDto) {
        productRepository.save(productFactory.toProductEntity(bookDto));
    }

    public void updateAllProducts(List<ProductEntity> productEntityList) {
        productRepository.saveAll(productEntityList);
    }

    public void updateProduct(Long id, ProductDto productDto) {
        productRepository.findById(id).ifPresentOrElse((productEntity) -> {
            ProductEntity toCustomerEntity = productFactory.toProductEntity(productDto);
            toCustomerEntity.setId(productEntity.getId());
            productRepository.save(toCustomerEntity);
        }, () -> {
            throw new DomainNotFoundException(PRODUCT_COULD_NOT_FOUND_TO_BE_UPDATED, id);
        });
    }

    public void deleteProduct(Long id) {
        productRepository.findById(id).ifPresentOrElse((productEntity) -> productRepository.deleteById(productEntity.getId()), () -> {
            throw new DomainNotFoundException(PRODUCT_COULD_NOT_FOUND_TO_BE_DELETED, id);
        });
    }
}
