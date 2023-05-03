package logistic.management.model.dto.response.transactionResponse;

import logistic.management.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class OrdersResponse extends BaseDto {

    private String orderNo;
    private String orderBy;
    private Double amount;
    private Double amountDue;
    private String orderStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String deliveryType;
    private Double deliveryCost;

    private List<OrderHelperResponse> orderItemsList;


}

