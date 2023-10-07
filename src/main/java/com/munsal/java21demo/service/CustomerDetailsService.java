package com.munsal.java21demo.service;

import com.munsal.java21demo.domain.entity.CustomerEntity;
import com.munsal.java21demo.domain.model.CustomerDetail;
import com.munsal.java21demo.factory.CustomerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {
  private final CustomerService customerService;
  private final CustomerFactory customerFactory;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    CustomerEntity customerEntity = customerService.getCustomerByUsername(username);
    return CustomerDetail.build(customerFactory.toCustomerDto(customerEntity));
  }

}
