package logistic.management.model.dto.response.servicesResponse;


import logistic.management.model.dto.BaseDto;
import lombok.Data;

@Data
public class DeliveryResponse extends BaseDto {

     private String deliveryType;
     private Double cost;

}
