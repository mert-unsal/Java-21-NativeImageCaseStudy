package com.munsal.java21demo.repository;

import com.munsal.java21demo.domain.model.StatisticInformationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class StatisticRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<StatisticInformationDto> getStatisticInformation() {
        Query nativeQuery = entityManager.createNativeQuery("select to_char(orders.created_date,'Mon') as month," +
                "       extract(year from orders.created_date) as year," +
                "       sum(orders.total_amount) as totalAmountPerMonth," +
                "       sum(ol.quantity) as totalBookCount," +
                "       count(*) as totalOrderCount from orders join order_line ol on orders.id = ol.order_id group by 1,2", "MonthlyStatisticResult");
        return (List<StatisticInformationDto>) nativeQuery.getResultList();
    }
}
