package com.munsal.java21demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.munsal.java21demo.domain.jsonView.ViewRole;
import com.munsal.java21demo.domain.model.CustomerDto;
import com.munsal.java21demo.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(CustomerController.BASE_PATH)
@RequiredArgsConstructor
public class CustomerController {
    public static final String BASE_PATH = "customer";
    private static final String TAG = "Customer Controllers";

    private final CustomerService customerService;

    @GetMapping("all")
    @Operation(tags = TAG, summary = "Get All Customers")
    @JsonView({ViewRole.ViewRequest.class})
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    @Operation(tags = TAG, summary = "Get Customer By Id")
    @JsonView({ViewRole.ViewRequest.class})
    public CustomerDto get(@PathVariable("id") Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @PutMapping("{id}")
    @Operation(tags = TAG, summary = "Update customer")
    @JsonView({ViewRole.UpsertRequest.class})
    public void update(@PathVariable Long id, @RequestBody @Valid CustomerDto CustomerDto) {
        customerService.updateCustomer(id, CustomerDto);
    }

    @DeleteMapping("{id}")
    @Operation(tags = TAG, summary = "Delete Customer")
    @JsonView({ViewRole.DeleteRequest.class})
    public void delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
