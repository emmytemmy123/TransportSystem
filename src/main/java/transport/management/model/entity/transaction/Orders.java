package transport.management.model.entity.transaction;


import transport.management.model.entity.BaseEntity;
import transport.management.model.entity.user.Customer;
import transport.management.model.listener.BaseListener;
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
    private Double amount;
    private Double amountDue;
    private String orderStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer serialNo;


    @ManyToOne
    @JoinColumn(name = "customerId", insertable = true, updatable = true)
    private Customer customer;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItems> orderItemsList;

    public Orders(){}


}
