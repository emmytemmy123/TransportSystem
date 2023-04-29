package transport.management.model.entity.user;

import transport.management.model.entity.BaseUser;
import transport.management.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@MappedSuperclass
@Data
@Entity
@EntityListeners(BaseListener.class)
@Table (name = "appUser")
@AllArgsConstructor
public class AppUser extends BaseUser {

    private String address2;


    public AppUser(){}

   }
