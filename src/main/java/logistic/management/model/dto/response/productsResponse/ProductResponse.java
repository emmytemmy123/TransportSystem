package logistic.management.model.dto.response.productsResponse;


import logistic.management.model.dto.BaseDto;
import lombok.Data;

@Data
public class ProductResponse extends BaseDto {

     private String category;
     private String model;
     private String name;
     private Integer quantity;
     private Double salesPrice;
     private Integer capacity;
     private String colour;
     private Integer height;
     private Integer width;
     private Integer depth;
     private Integer weight;
     private Integer warranty;
     private String photo;
     private String description;
     private String postedBy;


}
