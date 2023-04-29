package transport.management.model.entity.products;


import transport.management.model.entity.BaseEntity;
import transport.management.model.entity.services.Delivery;
import transport.management.model.entity.user.Customer;
import transport.management.model.entity.user.Sellers;
import transport.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


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
    private Integer durations;
    private Date expDate;
    private String description;
    private String postedBy;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "productTypeId", updatable = true)
    private ProductCategory productCategory;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "productId")
//    private Customer customer;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "productId")
//    private Sellers sellers;

    @OneToMany(mappedBy = "product")
    private List<Delivery> deliveryList;

    public Product(){}


}
