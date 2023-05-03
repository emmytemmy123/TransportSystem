package logistic.management.model.dto.response.transactionResponse;


import logistic.management.model.dto.BaseDto;
import lombok.Data;

@Data
public class PaymentResponse extends BaseDto {

     private Double amount;
     private String description;
     private String paidBy;
     private String paymentMode;
     private String paymentStatus;
     private String postedBy;
     private String tranReference;

     private PaymentHelper order;

}
