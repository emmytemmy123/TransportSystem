package logistic.management.model.entity.products;


import logistic.management.model.entity.BaseEntity;
import logistic.management.model.entity.user.Sellers;
import logistic.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "products")
@Data
public class Product extends BaseEntity {

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


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "productTypeId", updatable = true)
    private ProductCategory productCategory;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sellerId", insertable = true, updatable = true)
    private Sellers sellers;


    public Product(){}


}
