package logistic.management.model.entity.user;

import logistic.management.model.entity.BaseUser;
import logistic.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

//@MappedSuperclass
@Data
@Entity
@EntityListeners(BaseListener.class)
@Table (name = "appUser")
@AllArgsConstructor
public class AppUser extends BaseUser {

    private String roles;
    private String address2;


    public AppUser(){}

   }
