package com.munsal.java21demo.domain.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderLineResponse {
    private Integer id;
    private Integer quantity;
    private BigDecimal unitPrice;
    private String bookTitle;
    private String bookAuthor;
    private String bookISBN;
}