package com.munsal.java21demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.munsal.java21demo.domain.jsonView.ViewRole;
import com.munsal.java21demo.domain.model.OrderDto;
import com.munsal.java21demo.domain.response.OrderDetailResponse;
import com.munsal.java21demo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(OrderController.BASE_PATH)
@RequiredArgsConstructor
public class OrderController {
    public static final String BASE_PATH = "order";
    private static final String TAG = "Order Controllers";
    private final OrderService orderService;

    @PostMapping
    @Operation(tags = TAG, summary = "Place an order")
    @JsonView({ViewRole.AddRequest.class})
    public void add(@RequestBody @Valid OrderDto orderDto) {
        orderService.addOrder(orderDto);
    }


    @GetMapping("all")
    @Operation(tags = TAG, summary = "Get All Orders")
    @JsonView({ViewRole.ViewRequest.class})
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }


    @GetMapping("orderDetails")
    @Operation(tags = TAG, summary = "Get Order Details")
    public List<OrderDetailResponse> getAllOrderDetails(@RequestParam Long orderId) {
        return orderService.getOrderDetails(orderId);
    }

    @GetMapping("allByCustomerId")
    @Operation(tags = TAG, summary = "Get All Orders By Customer Id")
    @JsonView({ViewRole.ViewRequest.class})
    public List<OrderDto> getAllOrdersByCustomerId(@RequestParam Long customerId,
                                                   @RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "id") String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return orderService.getAllOrdersByCustomerId(customerId, paging);
    }


    @GetMapping("allByDate")
    @Operation(tags = TAG, summary = "Get All Orders By Date Range")
    @JsonView({ViewRole.ViewRequest.class})
    public List<OrderDto> getAllOrdersByDateRange(@RequestParam Long startDateTimestamp,
                                                  @RequestParam Long endDateTimestamp,
                                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "id") String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return orderService.getAllOrdersByDate(startDateTimestamp, endDateTimestamp, paging);
    }
}
