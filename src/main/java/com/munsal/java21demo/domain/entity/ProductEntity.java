package com.munsal.java21demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "product")
@SequenceGenerator(name = "product_seq_gen", allocationSize = 1, sequenceName = "product_seq")
public class ProductEntity extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    private Long id;
    @Column(name = "stock")
    private Long stock;
    @Column(name = "price")
    private BigDecimal price;
    @OneToOne
    private BookEntity bookEntity;
    @Version
    private Integer version;

}