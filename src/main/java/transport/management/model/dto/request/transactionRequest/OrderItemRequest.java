package transport.management.model.dto.request.transactionRequest;


import lombok.Data;
import transport.management.utills.MessageUtil;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class OrderItemRequest {

    @NotNull(message = MessageUtil.INVALID_NAME)
    @NotEmpty(message = MessageUtil.INVALID_NAME)
    private String itemName;

    @NotNull(message = MessageUtil.INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private Double salesPrice;

    @NotNull(message = MessageUtil.INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private Integer quantity;

    @NotNull(message = MessageUtil.INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private Double amount;

    @NotNull(message = MessageUtil.INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private Double purchasePrice;

    @NotNull(message = MessageUtil.INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private Double purchaseAmount;

    @NotNull(message = MessageUtil.INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private String serviceName;

    @NotNull(message = MessageUtil.INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private String description;

    private UUID productId;



}
