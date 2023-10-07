package com.munsal.java21demo.domain.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.munsal.java21demo.domain.jsonView.ViewRole;
import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderLineDto {
    @JsonView({ViewRole.DeleteRequest.class, ViewRole.ViewRequest.class })
    private Long id;
    @NotNull(message = "Quantity field cannot be null")
    @Min(value = 1, message = "Quantity should not be less than 1")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private Integer quantity;
    @JsonView({ViewRole.ViewRequest.class, ViewRole.UpsertRequest.class})
    private BigDecimal unitAmount;
    @NotNull(message = "Book id field cannot be null")
    @JsonView({ViewRole.DeleteRequest.class, ViewRole.AddRequest.class, ViewRole.ViewRequest.class })
    private Long bookId;
    @JsonView({ViewRole.DeleteRequest.class, ViewRole.ViewRequest.class })
    private Long orderId;
}
