package com.munsal.java21demo.service;

import com.munsal.java21demo.domain.entity.CustomerEntity;
import com.munsal.java21demo.domain.model.CustomerDto;
import com.munsal.java21demo.exception.DomainNotFoundException;
import com.munsal.java21demo.factory.CustomerFactory;
import com.munsal.java21demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.munsal.java21demo.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final CustomerFactory customerFactory;

    public List<CustomerDto> getAllCustomer() {
        return customerRepository.findAll().stream().map(customerFactory::toCustomerDto).toList();
    }

    public CustomerEntity getCustomerByUsername(String name) {
        return customerRepository.findByUsername(name).orElseThrow(() -> {
            throw new DomainNotFoundException(CUSTOMER_COULD_NOT_FOUND_GIVEN_USERNAME,name);
        });
    }

    public CustomerDto getCustomer(Long customerId) {
        return customerRepository.findById(customerId).map(customerFactory::toCustomerDto).orElseThrow(() -> {
            throw new DomainNotFoundException(CUSTOMER_COULD_NOT_FOUND,customerId);
        });
    }

    public CustomerEntity getCustomerEntity(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> {
            throw new DomainNotFoundException(CUSTOMER_COULD_NOT_FOUND,customerId);
        });
    }

    public void addCustomer(CustomerDto customerDto) {
        customerRepository.save(customerFactory.toCustomerEntity(customerDto));
    }

    public void updateCustomer(Long id, CustomerDto customerDto) {
        customerRepository.findById(id).ifPresentOrElse(customer -> {
            CustomerEntity toCustomerEntityEntity = customerFactory.toCustomerEntity(customerDto);
            toCustomerEntityEntity.setId(id);
            customerRepository.save(toCustomerEntityEntity);
        }, () -> {
            throw new DomainNotFoundException(CUSTOMER_COULD_NOT_FOUND_TO_BE_UPDATED,id);
        });
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id).ifPresentOrElse((customer) -> customerRepository.deleteById(customer.getId()),()->{
            throw new DomainNotFoundException(CUSTOMER_COULD_NOT_FOUND_TO_BE_DELETED,id);
        });
    }
}
