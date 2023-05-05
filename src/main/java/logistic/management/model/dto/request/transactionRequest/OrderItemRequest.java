package logistic.management.model.dto.request.transactionRequest;


import logistic.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class OrderItemRequest {


    @NotNull(message = MessageUtil.INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
    private Integer quantity;

    private UUID productId;



}
