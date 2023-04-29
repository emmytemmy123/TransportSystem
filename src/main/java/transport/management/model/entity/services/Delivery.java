package transport.management.model.entity.services;


import transport.management.model.entity.BaseEntity;
import transport.management.model.entity.products.Product;
import transport.management.model.entity.user.AppUser;
import transport.management.model.entity.user.Customer;
import transport.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "delivery")
public class Delivery extends BaseEntity {

    private String deliveryType;
    private Double cost;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", insertable = true, updatable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdById", insertable = true, updatable = true)
    private AppUser createdBy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", insertable = true, updatable = true)
    private Product product;

    public Delivery(){}

}
