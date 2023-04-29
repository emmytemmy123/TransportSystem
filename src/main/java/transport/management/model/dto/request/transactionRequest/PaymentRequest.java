package transport.management.model.dto.request.transactionRequest;

import lombok.Data;
import transport.management.utills.MessageUtil;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class PaymentRequest  {


       @NotNull(message = MessageUtil.INVALID_NAME)
       @NotEmpty(message = MessageUtil.INVALID_NAME)
       private String paymentMode;

       @NotNull(message = MessageUtil.INVALID_NAME)
       @Min(value=50, message = MessageUtil.INVALID_NAME)
       private Double amount;

       @NotNull(message = MessageUtil.INVALID_NAME)
       private UUID ordersId;

        @NotNull(message = MessageUtil.INVALID_NAME)
        private String tranReference;





}