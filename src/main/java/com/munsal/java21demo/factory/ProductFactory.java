package com.munsal.java21demo.factory;


import com.munsal.java21demo.domain.entity.BookEntity;
import com.munsal.java21demo.domain.entity.ProductEntity;
import com.munsal.java21demo.domain.model.ProductDto;
import com.munsal.java21demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFactory {
    private final BookService bookService;

    public ProductEntity toProductEntity(ProductDto productDto) {
        BookEntity bookEntity = bookService.getBookEntity(productDto.getBookId());
        return ProductEntity.builder()
                .bookEntity(bookEntity)
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .build();
    }

    public ProductDto toProductDto(ProductEntity productEntity) {
        return ProductDto.
                builder()
                .id(productEntity.getId())
                .stock(productEntity.getStock())
                .price(productEntity.getPrice())
                .bookId(productEntity.getBookEntity().getId())
                .build();
    }
}
