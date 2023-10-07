package com.munsal.java21demo.domain.model;


import com.fasterxml.jackson.annotation.JsonView;
import com.munsal.java21demo.domain.jsonView.ViewRole;
import lombok.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDto {
    @JsonView({ViewRole.DeleteRequest.class, ViewRole.AddRequest.class, ViewRole.ViewRequest.class})
    Long customerId;

    @NotEmpty(message = "Input movie list cannot be empty.")
    @JsonView({ViewRole.DeleteRequest.class, ViewRole.AddRequest.class, ViewRole.ViewRequest.class})
    List<@Valid OrderLineDto> orderLineDtoList;

    //    @Null(message = "TotalAmount value cannot be passed by outside.")
    @JsonView({ViewRole.DeleteRequest.class, ViewRole.ViewRequest.class})
    BigDecimal totalAmount;
}
