package fcmb.com.good.model.entity.others;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name="hotel")
public class Hotel extends BaseEntity {

    private String name;
    private String email;
    private String country;
    private String state;
    private String city;
    private String address;
    private String phone;

    public Hotel(){}


}
