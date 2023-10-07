package com.munsal.java21demo.domain.entity;

import com.munsal.java21demo.domain.model.StatisticInformationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@SqlResultSetMapping(name = "MonthlyStatisticResult", classes = {
        @ConstructorResult(targetClass = StatisticInformationDto.class,
                columns = {
                        @ColumnResult(name = "month", type = String.class),
                        @ColumnResult(name = "year", type = String.class),
                        @ColumnResult(name = "totalOrderCount", type = Long.class),
                        @ColumnResult(name = "totalBookCount", type = Long.class),
                        @ColumnResult(name = "totalAmountPerMonth", type = BigDecimal.class),
                })
})
@Table(name = "orders")
@SequenceGenerator(name = "order_seq_gen", allocationSize = 1, sequenceName = "order_seq")
public class OrderEntity extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_gen")
    private Long id;

    @JoinColumn(name = "customer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerEntity customerEntity;
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<OrderLineEntity> orderLineEntityList = new ArrayList<>();

    @Column(name = "totalAmount")
    private BigDecimal totalAmount;
}
