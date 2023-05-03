package logistic.management.model.entity.user;


import logistic.management.model.entity.BaseUser;
import logistic.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@EntityListeners(BaseListener.class)
@Table(name="customer")
@AllArgsConstructor
public class Customer extends BaseUser {

    private String nin;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdById", updatable = true)
    private AppUser createdBy;

//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
//    private List<Product> productList;



    public Customer(){

    }


}
