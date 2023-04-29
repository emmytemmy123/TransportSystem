package transport.management.model.dto.request.transactionRequest;


import lombok.Data;
import transport.management.utills.MessageUtil;

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


}
