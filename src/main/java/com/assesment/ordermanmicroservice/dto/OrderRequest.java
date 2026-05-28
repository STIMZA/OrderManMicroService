package com.assesment.ordermanmicroservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    @NotEmpty(message = "Order must contain at least one item")
    private List<ItemLine> items;

    @Data
    public static class ItemLine {
        @NotNull(message = "Product ID is mandatory")
        private Long productId;

        @NotNull(message = "Quantity is mandatory")
        @Min(value = 1, message = "Quantity must be at least 1")
        private Integer quantity;
    }
}