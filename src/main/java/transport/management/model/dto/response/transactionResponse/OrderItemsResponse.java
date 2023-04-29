package transport.management.model.dto.response.transactionResponse;

import transport.management.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderItemsResponse extends BaseDto {

    private String itemName;
    private Double salesPrice;
    private Integer quantity;
    private Double amount;
    private Double purchasePrice;
    private Double purchaseAmount;
    private Double profit;
    private String serviceName;
    private String description;
    private LocalDateTime transactionDate;
    private String status;



}
