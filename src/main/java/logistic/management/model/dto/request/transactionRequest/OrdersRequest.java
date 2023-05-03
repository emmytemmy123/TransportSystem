package logistic.management.model.dto.request.transactionRequest;


import logistic.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class OrdersRequest {

    @NotNull(message = MessageUtil.INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private UUID createdById;

    @NotNull(message = MessageUtil.INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private UUID customerId;

    private List<OrderItemRequest> items;

    private String deliveryType;


}
