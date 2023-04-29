package transport.management.model.dto.request.productsRequest;

import lombok.Data;
import transport.management.utills.MessageUtil;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.UUID;

import static transport.management.utills.MessageUtil.PRODUCT_NAME;

@Data
public class ProductCategoryRequest {


    @NotNull(message = PRODUCT_NAME)
    @NotEmpty(message = PRODUCT_NAME)
    private String name;

    @NotNull(message = PRODUCT_NAME)
    @NotEmpty(message = PRODUCT_NAME)
    private String description;

    @NotNull(message = PRODUCT_NAME)
//  @NotEmpty(message = PRODUCT_NAME)
    private UUID createdById;

}
