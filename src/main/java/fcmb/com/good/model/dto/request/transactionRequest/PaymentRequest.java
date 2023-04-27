package fcmb.com.good.model.dto.request.transactionRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class PaymentRequest  {


       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       private String paymentMode;

       @NotNull(message = INVALID_NAME)
       @Min(value=50, message = INVALID_NAME)
       private Double amount;

       @NotNull(message = INVALID_NAME)
       private UUID ordersId;

        @NotNull(message = INVALID_NAME)
        private String tranReference;





}
