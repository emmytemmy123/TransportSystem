package transport.management.model.entity.transaction;

import transport.management.model.entity.BaseEntity;
import transport.management.model.entity.products.Product;
import transport.management.model.entity.user.AppUser;
import transport.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "orderItems")
public class OrderItems extends BaseEntity {

    private String itemName;
    private Double salesPrice;
    private Integer quantity;
    private Double amount;
    private Double purchasePrice;
    private Double purchaseAmount;
    private Double profit;
    private String serviceName;
    private String description;
    private LocalDateTime transactionDate;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdById", insertable = true, updatable = true)
    private AppUser createdBy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ordersId", insertable = true, updatable = true)
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", insertable = true, updatable = true)
    private Product product;

    public OrderItems(){}

}