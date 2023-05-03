package logistic.management.model.entity.products;


import logistic.management.model.entity.BaseEntity;
import logistic.management.model.entity.user.AppUser;
import logistic.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "productCategory")
public class ProductCategory extends BaseEntity {

    private String name;
    private String description;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdById", updatable = true)
    private AppUser createdBy;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> productList;

    public ProductCategory(){}

}
