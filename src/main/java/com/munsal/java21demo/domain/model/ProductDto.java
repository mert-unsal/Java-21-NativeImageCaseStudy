package com.munsal.java21demo.domain.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.munsal.java21demo.domain.jsonView.ViewRole;
import lombok.*;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductDto {
    @JsonView({ViewRole.DeleteRequest.class, ViewRole.ViewRequest.class })
    private Long id;
    @NotNull(message = "Stock field cannot be null")
    @Min(value = 0, message = "Stock should not be less than 0")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private Long stock;
    @NotNull(message = "Price field cannot be null")
    @DecimalMin(value = "0.25", message = "Price should not be less than 0.25")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class, ViewRole.UpsertRequest.class})
    private BigDecimal price;

    @NotNull(message = "Book id field cannot be null")
    @JsonView({ViewRole.ViewRequest.class, ViewRole.AddRequest.class})
    private Long bookId;
}
