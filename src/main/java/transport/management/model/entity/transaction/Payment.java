package transport.management.model.entity.transaction;


import transport.management.model.entity.BaseEntity;
import transport.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {

    private Double amount;
    private String description;
    private String paymentMode;
    private String paymentStatus;
    private String postedBy;
    private String tranReference;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId", insertable = true, updatable = true)
    private Orders order;

    public Payment(){}


    }
