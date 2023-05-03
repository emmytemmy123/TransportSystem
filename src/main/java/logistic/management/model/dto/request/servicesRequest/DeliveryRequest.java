package logistic.management.model.dto.request.servicesRequest;

import logistic.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class DeliveryRequest {

        @NotNull(message = MessageUtil.INVALID_NAME)
        @NotEmpty(message = MessageUtil.INVALID_NAME)
        private String deliveryType;

        @NotNull(message = MessageUtil.INVALID_NAME)
        @Min(value=50, message = MessageUtil.INVALID_NAME)
        private Double cost;

        @NotNull(message = MessageUtil.INVALID_NAME)
        @NotEmpty(message = MessageUtil.INVALID_NAME)
        private UUID createdById;

        @NotNull(message = MessageUtil.INVALID_NAME)
        @NotEmpty(message = MessageUtil.INVALID_NAME)
        private UUID customerId;

        @NotNull(message = MessageUtil.INVALID_NAME)
        @NotEmpty(message = MessageUtil.INVALID_NAME)
        private UUID productId;

}
