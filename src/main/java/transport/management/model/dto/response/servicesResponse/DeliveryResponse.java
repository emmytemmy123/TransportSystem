package transport.management.model.dto.response.servicesResponse;


import transport.management.model.dto.BaseDto;
import lombok.Data;

@Data
public class DeliveryResponse extends BaseDto {

     private String deliveryType;
     private Double cost;

}
