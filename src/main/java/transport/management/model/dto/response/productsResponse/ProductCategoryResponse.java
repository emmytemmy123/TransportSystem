package transport.management.model.dto.response.productsResponse;


import transport.management.model.dto.BaseDto;
import lombok.Data;

@Data
public class ProductCategoryResponse extends BaseDto {

    private String name;
    private String description;
}
