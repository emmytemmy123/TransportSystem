package logistic.management.model.entity.transaction;


import logistic.management.model.entity.BaseEntity;
import logistic.management.model.listener.BaseListener;
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
    private Double balance;
    private String paymentMode;
    private String paymentStatus;
    private String paidBy;
    private String tranReference;
    private Integer serialNo;



    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId", insertable = true, updatable = true)
    private Orders order;

    public Payment(){}


    }
