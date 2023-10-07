package com.munsal.java21demo.factory;

import com.munsal.java21demo.domain.entity.CustomerEntity;
import com.munsal.java21demo.domain.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerFactory {
    public CustomerEntity toCustomerEntity(CustomerDto customerDto) {
        return CustomerEntity.builder()
                .username(customerDto.getUsername())
                .email(customerDto.getEmail())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .password(customerDto.getPassword())
                .address(customerDto.getAddress())
                .phone(customerDto.getPhone())
                .build();
    }


    public CustomerDto toCustomerDto(CustomerEntity customerEntity) {
        return CustomerDto.builder()
                .id(customerEntity.getId())
                .username(customerEntity.getUsername())
                .email(customerEntity.getEmail())
                .password(customerEntity.getPassword())
                .name(customerEntity.getName())
                .surname(customerEntity.getSurname())
                .address(customerEntity.getAddress())
                .phone(customerEntity.getPhone())
                .build();
    }

}
