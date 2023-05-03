package logistic.management.model.dto.request.productsRequest;

import logistic.management.utills.MessageUtil;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.UUID;

@Data
public class ProductRequest  {

     @NotNull(message = MessageUtil.INVALID_NAME)
     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String category;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String model;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String name;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @Min(value = 1, message = MessageUtil.INVALID_NAME)
     private Integer quantity;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @Min(value=50, message = MessageUtil.INVALID_NAME)
     private Double salesPrice;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @Min(value = 1, message = MessageUtil.INVALID_NAME)
     private Integer capacity;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String colour;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @Min(value = 1, message = MessageUtil.INVALID_NAME)
     private Integer height;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @Min(value = 1, message = MessageUtil.INVALID_NAME)
     private Integer width;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @Min(value = 1, message = MessageUtil.INVALID_NAME)
     private Integer depth;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @Min(value = 1, message = MessageUtil.INVALID_NAME)
     private Integer weight;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @Min(value = 1, message = MessageUtil.INVALID_NAME)
     private Integer warranty;

//     @NotNull(message = MessageUtil.INVALID_NAME)
//     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String photo;

     @NotNull(message = MessageUtil.INVALID_NAME)
     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String description;

//     @NotNull(message = MessageUtil.INVALID_NAME)
//     @NotEmpty(message = MessageUtil.INVALID_NAME)
     private String postedBy;

     @NotNull(message = MessageUtil.INVALID_NAME)
     private UUID categoryId;

     @NotNull(message = MessageUtil.INVALID_NAME)
     private UUID PostedById;



}
