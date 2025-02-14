package id.ac.ui.cs.advprog.eshop.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String productId;

    @Pattern(regexp = ".*\\S.*", message = "Product name cannot be only spaces")
    @NotBlank(message = "Product name is required")
    private String productName;

    @NotNull
    @Min(value = 1, message = "Quantity must be at least 1")
    private int productQuantity;
}