package fcmb.com.good.model.dto.response.productsResponse;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ProductFacilityResponse extends BaseDto {

    private String name;
    private String fileName;
    private String description;
    private Integer quantity;
    private String status;

    private ProductFacilityHelperResponse product;


}
