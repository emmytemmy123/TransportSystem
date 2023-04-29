package transport.management.model.entity.user;


import transport.management.model.entity.BaseUser;
import transport.management.model.entity.products.Product;
import transport.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "seller")
public class Sellers extends BaseUser {


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdById", updatable = true)
    private AppUser createdBy;

//    @OneToMany(mappedBy = "sellers", fetch = FetchType.LAZY)
//    private List<Product> productList;


    public Sellers(){}


    }
