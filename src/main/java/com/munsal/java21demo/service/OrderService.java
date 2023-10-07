package com.munsal.java21demo.service;


import com.munsal.java21demo.domain.entity.OrderEntity;
import com.munsal.java21demo.domain.entity.OrderLineEntity;
import com.munsal.java21demo.domain.entity.ProductEntity;
import com.munsal.java21demo.domain.model.OrderDto;
import com.munsal.java21demo.domain.response.OrderDetailResponse;
import com.munsal.java21demo.exception.InsufficientStockException;
import com.munsal.java21demo.factory.OrderFactory;
import com.munsal.java21demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.munsal.java21demo.exception.ErrorCode.INSUFFICIENT_STOCK;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final ProductService productService;

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(orderFactory::toOrderDto).toList();
    }

    public List<OrderDto> getAllOrdersByCustomerId(Long customerId, Pageable paging) {
        return orderRepository.findAllByCustomerEntity_Id(customerId, paging).stream().map(orderFactory::toOrderDto).toList();
    }

    public List<OrderDto> getAllOrdersByDate(Long startDate, Long endDate, Pageable paging) {
        return orderRepository.findAllByCreatedDateAfterAndCreatedDateBefore(new Date(startDate), new Date(endDate), paging).stream().map(orderFactory::toOrderDto).toList();
    }

    @Transactional
    public void addOrder(OrderDto orderDto) {
        List<ProductEntity> productEntityList = new ArrayList<>();
        OrderEntity orderEntity = orderFactory.toOrderEntity(orderDto);
        boolean allProductHasSufficientQuantity = true;
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderLineEntity orderLineEntity : orderEntity.getOrderLineEntityList()) {
            Long quantity = Long.valueOf(orderLineEntity.getQuantity());
            Long bookId = orderLineEntity.getBookEntity().getId();
            ProductEntity productEntityByBookIdAndStock = productService.getProductEntityByBookIdAndStock(bookId, quantity);
            if (Objects.nonNull(productEntityByBookIdAndStock)) {
                productEntityByBookIdAndStock.setStock(productEntityByBookIdAndStock.getStock() - orderLineEntity.getQuantity());
                orderLineEntity.setUnitAmount(productEntityByBookIdAndStock.getPrice());
                totalAmount = totalAmount.add(productEntityByBookIdAndStock.getPrice().multiply(BigDecimal.valueOf(orderLineEntity.getQuantity())));
                productEntityList.add(productEntityByBookIdAndStock);
            } else {
                allProductHasSufficientQuantity = false;
                break;
            }
        }
        if (allProductHasSufficientQuantity) {
            orderEntity.setTotalAmount(totalAmount);
            orderRepository.save(orderEntity);
            productService.updateAllProducts(productEntityList);
        } else {
            throw new InsufficientStockException(INSUFFICIENT_STOCK);
        }
    }

    public List<OrderDetailResponse> getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId).stream().map(orderFactory::toOrderDetailResponse).toList();
    }
}
