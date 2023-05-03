package logistic.management.model.dto.request.productsRequest;

import logistic.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.UUID;

@Data
public class ProductCategoryRequest {


    @NotNull(message = MessageUtil.PRODUCT_NAME)
    @NotEmpty(message = MessageUtil.PRODUCT_NAME)
    private String name;

    @NotNull(message = MessageUtil.PRODUCT_NAME)
    @NotEmpty(message = MessageUtil.PRODUCT_NAME)
    private String description;

    @NotNull(message = MessageUtil.PRODUCT_NAME)
//  @NotEmpty(message = PRODUCT_NAME)
    private UUID createdById;

}
