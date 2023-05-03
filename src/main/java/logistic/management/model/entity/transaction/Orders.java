package logistic.management.model.entity.transaction;


import logistic.management.model.entity.BaseEntity;
import logistic.management.model.entity.user.Customer;
import logistic.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "orders")
public class Orders extends BaseEntity {

    private String orderNo;
    private String orderBy;
    private Double totalAmount;
    private Double totalAmountDue;
    private String orderStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer serialNo;
    private String deliveryType;
    private Double deliveryCost;
    private Integer totalQuantity;


    @ManyToOne
    @JoinColumn(name = "customerId", insertable = true, updatable = true)
    private Customer customer;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItems> orderItemsList;


    public Orders(){}


}
