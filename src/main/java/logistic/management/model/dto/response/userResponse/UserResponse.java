package logistic.management.model.dto.response.userResponse;

import logistic.management.model.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse extends BaseDto {

     private String name;
     private String email;
     private String phone;
     private String address;
     private String photo;
     private String gender;
     private String country;
     private String city;
     private String nin;
     private String username;
     private String roles;
     private String createdBy;



}
