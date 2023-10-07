package com.munsal.java21demo.domain.response;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDetailResponse {
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private List<OrderLineResponse> orderLineList;
}
