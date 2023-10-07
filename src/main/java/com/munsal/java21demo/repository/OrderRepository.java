package com.munsal.java21demo.repository;


import com.munsal.java21demo.domain.entity.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    List<OrderEntity> findAll();

    List<OrderEntity> findAllByCustomerEntity_Id(Long customerId, Pageable pageable);

    List<OrderEntity> findAllByCreatedDateAfterAndCreatedDateBefore(Date startDate, Date endDate, Pageable pageable);
}
