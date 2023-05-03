package logistic.management.model.dto.response.transactionResponse;

import logistic.management.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderItemsResponse extends BaseDto {

    private String itemName;
    private Double salesPrice;
    private Integer quantity;
    private Double amount;
    private String description;
    private LocalDateTime transactionDate;
    private String status;



}
