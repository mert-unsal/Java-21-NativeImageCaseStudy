package com.munsal.java21demo.factory;

import com.munsal.java21demo.domain.entity.CustomerEntity;
import com.munsal.java21demo.domain.entity.OrderEntity;
import com.munsal.java21demo.domain.entity.OrderLineEntity;
import com.munsal.java21demo.domain.model.OrderDto;
import com.munsal.java21demo.domain.model.OrderLineDto;
import com.munsal.java21demo.domain.response.OrderDetailResponse;
import com.munsal.java21demo.domain.response.OrderLineResponse;
import com.munsal.java21demo.service.BookService;
import com.munsal.java21demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFactory {
    private final CustomerService customerService;
    private final BookService bookService;

    public OrderEntity toOrderEntity(OrderDto orderDto) {
        CustomerEntity customerEntity = customerService.getCustomerEntity(orderDto.getCustomerId());
        return OrderEntity.builder()
                .customerEntity(customerEntity)
                .orderLineEntityList(orderDto.getOrderLineDtoList().stream().map(this::toOrderLineEntity).toList())
                .build();
    }
    public OrderDto toOrderDto(OrderEntity orderEntity) {
        return OrderDto.
                builder()
                .customerId(orderEntity.getCustomerEntity().getId())
                .orderLineDtoList(orderEntity.getOrderLineEntityList().stream().map(this::toOrderLineDto).toList())
                .totalAmount(orderEntity.getTotalAmount())
                .build();
    }

    public OrderDetailResponse toOrderDetailResponse(OrderEntity orderEntity) {
        return OrderDetailResponse.builder()
                .customerEmail(orderEntity.getCustomerEntity().getEmail())
                .customerName(orderEntity.getCustomerEntity().getName())
                .customerSurname(orderEntity.getCustomerEntity().getSurname())
                .orderLineList(orderEntity.getOrderLineEntityList().stream().map(this::toOrderLineResponse).toList())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLineEntity orderLineEntity) {
        return OrderLineResponse.builder()
                .id(orderLineEntity.getId().intValue())
                .bookTitle(orderLineEntity.getBookEntity().getTitle())
                .bookAuthor(orderLineEntity.getBookEntity().getAuthor())
                .bookISBN(orderLineEntity.getBookEntity().getIsbn())
                .quantity(orderLineEntity.getQuantity())
                .unitPrice(orderLineEntity.getUnitAmount())
                .build();
    }


    public OrderLineEntity toOrderLineEntity(OrderLineDto orderLineDto) {
        return OrderLineEntity
                .builder()
                .bookEntity(bookService.getBookEntity(orderLineDto.getBookId()))
                .unitAmount(orderLineDto.getUnitAmount())
                .quantity(orderLineDto.getQuantity())
                .build();
    }

    public OrderLineDto toOrderLineDto(OrderLineEntity orderLineEntity) {
        return OrderLineDto
                .builder()
                .id(orderLineEntity.getId())
                .bookId(orderLineEntity.getBookEntity().getId())
                .unitAmount(orderLineEntity.getUnitAmount())
                .quantity(orderLineEntity.getQuantity())
                .orderId(orderLineEntity.getOrderEntity().getId())
                .build();
    }
}
